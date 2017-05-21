package com.hiveview.entity;

/**
 * Created by zhangsw on 2017/5/21.
 */
public class DataFromLodon {

    private String type;//品种代号
    private String price;//最新价
    private String changepercent;//涨跌幅
    private String changequantity;//涨跌量
    private String openingprice;//开盘价
    private String maxprice;//最高价
    private String minprice;//最低价
    private String lastclosingprice;//昨收盘价
    private String amplitude;//振幅
    private String buyprice;//买入价
    private String sellprice;//卖出价
    private String updatetime;//更新时间

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(String changepercent) {
        this.changepercent = changepercent;
    }

    public String getChangequantity() {
        return changequantity;
    }

    public void setChangequantity(String changequantity) {
        this.changequantity = changequantity;
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

    public String getLastclosingprice() {
        return lastclosingprice;
    }

    public void setLastclosingprice(String lastclosingprice) {
        this.lastclosingprice = lastclosingprice;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
