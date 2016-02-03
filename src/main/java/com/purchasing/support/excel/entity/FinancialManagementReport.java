package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class FinancialManagementReport {

    private String month;
    private String costCenter;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<FinancialManagementReport> convertListObjectInList(Collection<Object> objects){
        List<FinancialManagementReport> financialManagementReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            FinancialManagementReport financialManagementReport =  new FinancialManagementReport();
            financialManagementReport.setMonth(arrayObj[0].toString());
            financialManagementReport.setCostCenter(arrayObj[1].toString());
            financialManagementReport.setValue(arrayObj[2].toString().replace(".",","));

            financialManagementReports.add(financialManagementReport);
        }
        return financialManagementReports;
    }
}
