package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vanessa on 12/29/15.
 */
public class PaymentForecastReport {

    private String orderNumber;
    private String supplier;
    private String costCenter;
    private String totalPriceCostCenter;
    private String alreadyPurchased;
    private String investmentPurchase;
    private String formPayment;
    private String dateFirstInstallment;
    private String dateLastInstallment;
    private String dateInput;
    private String expirateDate;
    private String inputPrice;
    private String sharePrice;
    private String totalFinalPrice;


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String order_number) {
        this.orderNumber = order_number;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getTotalPriceCostCenter() {
        return totalPriceCostCenter;
    }

    public void setTotalPriceCostCenter(String totalPriceCostCenter) {
        this.totalPriceCostCenter = totalPriceCostCenter;
    }

    public String getAlreadyPurchased() {
        return alreadyPurchased;
    }

    public void setAlreadyPurchased(String alreadyPurchased) {
        this.alreadyPurchased = alreadyPurchased;
    }

    public String getInvestmentPurchase() {
        return investmentPurchase;
    }

    public void setInvestmentPurchase(String investmentPurchase) {
        this.investmentPurchase = investmentPurchase;
    }

    public String getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(String formPayment) {
        this.formPayment = formPayment;
    }

    public String getDateFirstInstallment() {
        return dateFirstInstallment;
    }

    public void setDateFirstInstallment(String dateFirstInstallment) {
        this.dateFirstInstallment = dateFirstInstallment;
    }

    public String getDateLastInstallment() {
        return dateLastInstallment;
    }

    public void setDateLastInstallment(String dateLastInstallment) {
        this.dateLastInstallment = dateLastInstallment;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getExpirateDate() {
        return expirateDate;
    }

    public void setExpirateDate(String expirateDate) {
        this.expirateDate = expirateDate;
    }

    public String getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(String inputPrice) {
        this.inputPrice = inputPrice;
    }

    public String getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(String sharePrice) {
        this.sharePrice = sharePrice;
    }

    public String getTotalFinalPrice() {
        return totalFinalPrice;
    }

    public void setTotalFinalPrice(String totalFinalPrice) {
        this.totalFinalPrice = totalFinalPrice;
    }

    public List<PaymentForecastReport> convertListObjectInList(Collection<Object> objects){
        List<PaymentForecastReport> paymentForecastReports = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;
        for (int i = 0 ; i < arrayObject.length ; i ++){
            arrayObj = (Object[]) arrayObject[i];
            PaymentForecastReport paymentForecastReport =  new PaymentForecastReport();
            paymentForecastReport.setOrderNumber(arrayObj[0].toString());
            paymentForecastReport.setSupplier(arrayObj[1].toString());
            paymentForecastReport.setCostCenter(arrayObj[2].toString());
            paymentForecastReport.setTotalPriceCostCenter(arrayObj[3].toString());
            paymentForecastReport.setAlreadyPurchased(arrayObj[4].toString());
            paymentForecastReport.setInvestmentPurchase(arrayObj[5].toString());
            paymentForecastReport.setFormPayment(arrayObj[6].toString());
            paymentForecastReport.setDateFirstInstallment(arrayObj[7].toString());
            paymentForecastReport.setDateLastInstallment(arrayObj[8].toString());
            paymentForecastReport.setDateInput(arrayObj[9].toString());
            paymentForecastReport.setExpirateDate(arrayObj[10].toString());
            paymentForecastReport.setInputPrice(arrayObj[11].toString());
            paymentForecastReport.setSharePrice(arrayObj[12].toString());
            paymentForecastReport.setTotalFinalPrice(arrayObj[13].toString());

            paymentForecastReports.add(paymentForecastReport);
        }


        return paymentForecastReports;
    }


}
