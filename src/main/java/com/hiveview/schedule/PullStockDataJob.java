package com.hiveview.schedule;

import com.hiveview.common.Contans;
import com.hiveview.entity.DataFromLodon;
import com.hiveview.entity.DataFromShgold;
import com.hiveview.entity.StockData;
import com.hiveview.entity.StockProduct;
import com.hiveview.service.StockDataService;
import com.hiveview.service.StockProductService;
import com.hiveview.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangsw on 2017/5/21.
 */
public class PullStockDataJob  {
    @Autowired
    private static  StockDataService stockDataService;
    @Autowired
    private static StockProductService stockProductService;

    /**
     *获取最新数据
     */
    public static  void pull(){
        String url= Contans.SH_GOLD_URL+"?appkey="+Contans.APPKEY;
        String result = null;
        while (1==1) {
            try {
                result = HttpUtil.reqGet(url);
                JSONObject json = JSONObject.fromObject(result);
                if (json.getInt("status") != 0) {
                    System.out.println(json.getString("msg"));
                } else {
                    JSONArray resultarr = json.optJSONArray("result");
                    List<StockData> list = new ArrayList<>();
                    for (int i = 0; i < resultarr.size(); i++) {
                        JSONObject obj = (JSONObject) resultarr.opt(i);
                        DataFromShgold data = (DataFromShgold) JSONObject.toBean(obj, DataFromLodon.class);
                        StockData stockData = new StockData();

                        StockProduct stockProduct = stockProductService.getProuctByType(data.getType());
                        stockData.setProductId(stockProduct.getId());
                        stockData.setPrice(new BigDecimal(data.getPrice()));
                        stockData.setOpeningPrice(new BigDecimal(data.getOpeningprice()));
                        stockData.setLastClosingPrice(new BigDecimal(data.getLastclosingprice()));
                        stockData.setMaxPrice(new BigDecimal(data.getMaxprice()));
                        stockData.setMinPrice(new BigDecimal(data.getMinprice()));
                        stockData.setTradeAmount(new BigDecimal(data.getTradeamount()));
                        stockData.setChangeQuantity(new BigDecimal(data.getChangepercent()));
                        stockData.setDataTime(new Date(data.getUpdatetime()));

                        list.add(stockData);
                    }
                    //保存数据
                    stockDataService.saveDatas(list);
                }
                Thread.sleep(1000);//1000 毫秒，也就是1秒.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
