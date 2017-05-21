package com.hiveview.schedule;

import com.hiveview.contants.Constants;
import com.hiveview.contants.ContantType;
import com.hiveview.entity.StockData;
import com.hiveview.entity.StockOrder;
import com.hiveview.entity.StockRevenueModel;
import com.hiveview.entity.User;
import com.hiveview.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangsw on 2017/5/21.
 */
public class ClearOrdersJob {
    @Autowired
    private static StockDataService stockDataService;
    @Autowired
    private  static StockRevenueModelService stockRevenueModelService;
    @Autowired
    private static StockOrderService stockOrderService;
    @Autowired
    private static UserService userService;

    public static  void clearing(){

        while (1==1) {
            //获取最新数据
            StockData data = stockDataService.getOneFreshDataForM5();
            BigDecimal newPrice = data.getPrice();
            //获取未结算的订单
            List<StockOrder> list = stockOrderService.getStockOrdersByStatus(StockOrder.STATUS_HOLDING);
            for (StockOrder order : list) {

                //营销模式
                StockRevenueModel model = stockRevenueModelService.getModelById(order.getModelId());
                //模式要求涨跌量
                BigDecimal quantity = new BigDecimal(model.getChangeQuantity());

                BigDecimal val = null;
                int flag = -1;
                //买涨而且当前价格大于等于买入价
                if (order.getBuyGoing() == Constants.BUY_GOING_ZHANG && newPrice.compareTo(order.getBuyPrice()) >= 0) {
                    val = newPrice.subtract(order.getBuyPrice());
                    flag = val.compareTo(quantity);
                    //买跌而且当前价格小于买入价
                } else if(order.getBuyGoing() == Constants.BUY_GOING_DIE && newPrice.compareTo(order.getBuyPrice()) < 0){
                    val = order.getBuyPrice().subtract(newPrice);
                    flag = val.compareTo(quantity);
                }

                Double revenueNum = model.getRevenueNum();//收益百分比
                BigDecimal buyAmount =order.getBuyAmount();

                if (flag >= 0) {//赚了
                    //盈利金额=购买金额*收益百分比+购买金额
                    BigDecimal revenueAmount=buyAmount.multiply(new BigDecimal(revenueNum).divide(new BigDecimal(100))).add(buyAmount);
                    order.setRevenueAmount(revenueAmount);
                    order.setUpdateTime(new Date());
                    order.setSellPrice(newPrice);
                    order.setClosePositionTime(new Date());
                    order.setChangeQuantity(val);
                    order.setOrderStatus(StockOrder.STATUS_ZHIYING);
                    //修改用户余额
                    //获取用户信息 检查余额
                    User user=userService.getUserByUnionid(order.getUnionid());
                    BigDecimal balance=user.getBalance();
                    BigDecimal newBalance=balance.add(revenueAmount);
                    userService.updateUserBalance(user.getId(),newBalance, ContantType.balanceLogType_6,"止盈收入",true);

                } else {
                    order.setRevenueAmount(new BigDecimal("-"+buyAmount.toString()));
                    order.setUpdateTime(new Date());
                    order.setSellPrice(newPrice);
                    order.setClosePositionTime(new Date());
                    order.setChangeQuantity(val);
                    order.setOrderStatus(StockOrder.STATUS_ZHISUN);
                }

            }
        }
    }

}
