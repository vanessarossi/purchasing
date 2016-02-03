package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class PurchasedProductClassificationReport {

    private String month;
    private String category;
    private String value;
    private String total;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public List<PurchasedProductClassificationReport> convertListObjectInList(Collection<Object> objects){
        List<PurchasedProductClassificationReport> purchasedProductClassificationReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PurchasedProductClassificationReport purchasedProductClassificationReport =  new PurchasedProductClassificationReport();
            purchasedProductClassificationReport.setMonth(arrayObj[0].toString());
            purchasedProductClassificationReport.setCategory(arrayObj[1].toString());
            purchasedProductClassificationReport.setValue(arrayObj[2].toString().replace(".",","));
            purchasedProductClassificationReport.setTotal(arrayObj[3].toString().replace(".",","));

            purchasedProductClassificationReports.add(purchasedProductClassificationReport);
        }
        return purchasedProductClassificationReports;
    }
}
