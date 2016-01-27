package com.purchasing.support.excel.entity;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class PurchaseOrderForSupplierAndExpirationDateReport {

    private Integer code;
    private String taxCode;
    private Double total;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
