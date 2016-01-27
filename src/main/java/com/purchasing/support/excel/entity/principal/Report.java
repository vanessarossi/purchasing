package com.purchasing.support.excel.entity.principal;

import java.util.Date;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class Report {

    private Integer year;
    private Integer initialMonth;
    private Integer finalMonth;
    private Integer supplier;
    private Date expirationDate;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getInitialMonth() {
        return initialMonth;
    }

    public void setInitialMonth(Integer initialMonth) {
        this.initialMonth = initialMonth;
    }

    public Integer getFinalMonth() {
        return finalMonth;
    }

    public void setFinalMonth(Integer finalMonth) {
        this.finalMonth = finalMonth;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
