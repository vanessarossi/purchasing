package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class PurchaseOrderForSupplierAndExpirationDateReport {

    private String code;
    private String taxCode;
    private String total;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<PurchaseOrderForSupplierAndExpirationDateReport> convertListObjectInList(Collection<Object> objects){
        List<PurchaseOrderForSupplierAndExpirationDateReport> purchaseOrderForSupplierAndExpirationDateReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PurchaseOrderForSupplierAndExpirationDateReport purchaseOrderForSupplierAndExpirationDateReport =  new PurchaseOrderForSupplierAndExpirationDateReport();
            purchaseOrderForSupplierAndExpirationDateReport.setCode(arrayObj[0].toString());
            purchaseOrderForSupplierAndExpirationDateReport.setTaxCode(arrayObj[1].toString());
            purchaseOrderForSupplierAndExpirationDateReport.setTotal(arrayObj[2].toString().replace(".", ","));

            purchaseOrderForSupplierAndExpirationDateReports.add(purchaseOrderForSupplierAndExpirationDateReport);
        }
        return purchaseOrderForSupplierAndExpirationDateReports;
    }
}
