package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vanessa
 * @date 1/27/16
 */
public class FinancialManagementByCostCenterReport {

    private String month;
    private String costCenter;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<FinancialManagementByCostCenterReport> convertListObjectInList(Collection<Object> objects){
        List<FinancialManagementByCostCenterReport> financialManagementByCostCenterReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            FinancialManagementByCostCenterReport financialManagementByCostCenterReport =  new FinancialManagementByCostCenterReport();
            financialManagementByCostCenterReport.setMonth(arrayObj[0].toString());
            financialManagementByCostCenterReport.setCostCenter(arrayObj[1].toString());
            financialManagementByCostCenterReport.setTotal(arrayObj[2].toString().replace(".",","));

            financialManagementByCostCenterReports.add(financialManagementByCostCenterReport);
        }
        return financialManagementByCostCenterReports;
    }
}
