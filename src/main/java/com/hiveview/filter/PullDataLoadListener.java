package com.hiveview.filter;

import com.hiveview.schedule.ClearOrdersJob;
import com.hiveview.schedule.PullStockDataJob;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zhangsw on 2017/5/21.
 */
public class PullDataLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("获取最新数据开始**********************************");
        PullStockDataJob.pull();
        System.out.println("结算订单开始*************************************");
        ClearOrdersJob.clearing();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
