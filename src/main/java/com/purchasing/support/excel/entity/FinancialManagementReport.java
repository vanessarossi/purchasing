package com.purchasing.support.excel.entity;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class FinancialManagementReport {

    private Integer month;
    private String costCenter;
    private Double value;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
