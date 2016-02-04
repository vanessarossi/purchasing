package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class PurchaseOrderAndSolicitationReport {

    private String month;
    private String total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<PurchaseOrderAndSolicitationReport> convertListObjectInList(Collection<Object> objects){
        List<PurchaseOrderAndSolicitationReport> purchaseOrderAndSolicitationReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PurchaseOrderAndSolicitationReport purchaseOrderAndSolicitationReport =  new PurchaseOrderAndSolicitationReport();
            purchaseOrderAndSolicitationReport.setMonth(arrayObj[0].toString());
            purchaseOrderAndSolicitationReport.setTotal(arrayObj[1].toString());

            purchaseOrderAndSolicitationReports.add(purchaseOrderAndSolicitationReport);
        }
        return purchaseOrderAndSolicitationReports;
    }
}
