package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 3/7/16
 */
public class PurchasedProductClassificationByCostCenterReport {

    private String month;
    private String costCenter;
    private String category;
    private String value;
    private String total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<PurchasedProductClassificationByCostCenterReport> convertListObjectInList(Collection<Object> objects){
        List<PurchasedProductClassificationByCostCenterReport> purchasedProductClassificationByCostCenterReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PurchasedProductClassificationByCostCenterReport purchasedProductClassificationByCostCenterReport =  new PurchasedProductClassificationByCostCenterReport();
            purchasedProductClassificationByCostCenterReport.setMonth(arrayObj[0].toString());
            purchasedProductClassificationByCostCenterReport.setCostCenter(arrayObj[1].toString());
            purchasedProductClassificationByCostCenterReport.setCategory(arrayObj[2].toString());
            purchasedProductClassificationByCostCenterReport.setValue(arrayObj[3].toString().replace(".",","));
            purchasedProductClassificationByCostCenterReport.setTotal(arrayObj[4].toString().replace(".",","));

            purchasedProductClassificationByCostCenterReports.add(purchasedProductClassificationByCostCenterReport);
        }
        return purchasedProductClassificationByCostCenterReports;
    }
}
