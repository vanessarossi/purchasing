package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 3/7/16
 */
public class PurchasedServiceTypeByCostCenterReport {

    private String month;
    private String costCenter;
    private String typeService;
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

    public List<PurchasedServiceTypeByCostCenterReport> convertListObjectInList(Collection<Object> objects){
        List<PurchasedServiceTypeByCostCenterReport> purchasedServiceTypeByCostCenterReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PurchasedServiceTypeByCostCenterReport purchasedServiceTypeByCostCenterReport =  new PurchasedServiceTypeByCostCenterReport();
            purchasedServiceTypeByCostCenterReport.setMonth(arrayObj[0].toString());
            purchasedServiceTypeByCostCenterReport.setCostCenter(arrayObj[1].toString());
            purchasedServiceTypeByCostCenterReport.setTypeService(arrayObj[2].toString());
            purchasedServiceTypeByCostCenterReport.setValue(arrayObj[3].toString().replace(".",","));
            purchasedServiceTypeByCostCenterReport.setTotal(arrayObj[4].toString().replace(".",","));

            purchasedServiceTypeByCostCenterReports.add(purchasedServiceTypeByCostCenterReport);
        }
        return purchasedServiceTypeByCostCenterReports;
    }
}
