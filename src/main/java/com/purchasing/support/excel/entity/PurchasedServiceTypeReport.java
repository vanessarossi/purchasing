package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<PurchasedServiceTypeReport> convertListObjectInList(Collection<Object> objects){
        List<PurchasedServiceTypeReport> purchasedServiceTypeReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PurchasedServiceTypeReport purchasedServiceTypeReport =  new PurchasedServiceTypeReport();
            purchasedServiceTypeReport.setMonth(arrayObj[0].toString());
            purchasedServiceTypeReport.setTypeService(arrayObj[1].toString());
            purchasedServiceTypeReport.setValue(arrayObj[2].toString().replace(".",","));
            purchasedServiceTypeReport.setTotal(arrayObj[3].toString().replace(".",","));

            purchasedServiceTypeReports.add(purchasedServiceTypeReport);
        }
        return purchasedServiceTypeReports;
    }
}
