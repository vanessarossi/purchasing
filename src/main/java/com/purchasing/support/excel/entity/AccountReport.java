package com.purchasing.support.excel.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by vanessa on 08/08/2016.
 */
public class AccountReport {


    private String type;
    private String competence;
    private String place;
    private String address;
    private String phone;
    private String signatureType;
    private String typeService;
    private String value;
    private String discount;
    private String totalValue;
    private String expirationDate;
    private String observation;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(String signatureType) {
        this.signatureType = signatureType;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public List<AccountReport> convertListObjectReport(Collection<Object> objects){
        List<AccountReport> accounts = new ArrayList<>();
        Object arrayObject[] = objects.toArray();
        Object arrayObj[] = null;

        for (int i = 0; i < arrayObject.length; i++){
            arrayObj = (Object[]) arrayObject[i];
            AccountReport accountReport = new AccountReport();
                accountReport.setCompetence(arrayObj[0].toString());
                accountReport.setType(arrayObj[1].toString());
                accountReport.setPlace(arrayObj[2].toString());
                accountReport.setPhone(arrayObj[3].toString());
                accountReport.setSignatureType(arrayObj[4].toString());
                accountReport.setTypeService(arrayObj[5].toString());
                accountReport.setValue(arrayObj[6].toString().replace('.',','));
                accountReport.setDiscount(arrayObj[7].toString().replace('.',','));
                 accountReport.setTotalValue(arrayObj[8].toString().replace('.',','));
                accountReport.setExpirationDate(arrayObj[9].toString());
                accountReport.setObservation(arrayObj[10].toString());
            accounts.add(accountReport);
        }
        return accounts;
    }
}
