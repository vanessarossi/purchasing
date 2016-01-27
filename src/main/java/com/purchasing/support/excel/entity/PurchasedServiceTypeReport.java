package com.purchasing.support.excel.entity;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class PurchasedServiceTypeReport {

    private Integer month;
    private String typeService;
    private Double value;
    private Float total;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
