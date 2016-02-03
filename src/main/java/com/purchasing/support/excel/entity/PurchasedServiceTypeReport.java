package com.purchasing.support.excel.entity;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class PurchasedServiceTypeReport {

    private String month;
    private String typeService;
    private String value;
    private String total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
