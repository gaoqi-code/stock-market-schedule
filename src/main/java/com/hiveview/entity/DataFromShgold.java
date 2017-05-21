package com.hiveview.entity;

/**
 * Created by zhangsw on 2017/5/21.
 */
public class DataFromShgold {
    private String type;//品种代号
    private String typename;//品种名称
    private String price;//最新价
    private String openingprice;//开盘价
    private String maxprice;//最高价
    private String minprice;//最低价
    private String changepercent;//涨跌幅
    private String lastclosingprice;//昨收盘价
    private String tradeamount;//总成交量
    private String updatetime;//更新时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpeningprice() {
        return openingprice;
    }

    public void setOpeningprice(String openingprice) {
        this.openingprice = openingprice;
    }

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    public String getMinprice() {
        return minprice;
    }

    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    public String getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(String changepercent) {
        this.changepercent = changepercent;
    }

    public String getLastclosingprice() {
        return lastclosingprice;
    }

    public void setLastclosingprice(String lastclosingprice) {
        this.lastclosingprice = lastclosingprice;
    }

    public String getTradeamount() {
        return tradeamount;
    }

    public void setTradeamount(String tradeamount) {
        this.tradeamount = tradeamount;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
