package com.purchasing.support.report;

import com.purchasing.entity.Reception;
import com.purchasing.support.date.Conversor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanessa
 */
public class ReceptionByDateView {

    private String numberPurchaseOrder;
    private String supplier;
    private String numberTaxDocument;
    private String expirationDate;
    private String price;
    private String receptorName;

    public String getNumberPurchaseOrder() {
        return numberPurchaseOrder;
    }

    public void setNumberPurchaseOrder(String numberPurchaseOrder) {
        this.numberPurchaseOrder = numberPurchaseOrder;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNumberTaxDocument() {
        return numberTaxDocument;
    }

    public void setNumberTaxDocument(String numberTaxDocument) {
        this.numberTaxDocument = numberTaxDocument;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReceptorName() {
        return receptorName;
    }

    public void setReceptorName(String receptorName) {
        this.receptorName = receptorName;
    }

    public List<ReceptionByDateView> generateList(List<Reception> receptions){
        List<ReceptionByDateView> receptionByDateViews = new ArrayList<>();
        for (Reception reception : receptions) {
            ReceptionByDateView receptionByDateView = new ReceptionByDateView();
            receptionByDateView.setNumberPurchaseOrder(reception.getPurchaseOrder().getId().toString());
            receptionByDateView.setSupplier(reception.getPurchaseOrder().getBudget().getSupplier().getPerson().getName());
            receptionByDateView.setNumberTaxDocument(reception.getTaxDocument());
            receptionByDateView.setExpirationDate(Conversor.converterDateInString(reception.getPaymentInformation().getExpirationDate()));
            receptionByDateView.setPrice(reception.getPaymentInformation().getTotalFinalPrice().toString().replace(".",","));
            receptionByDateView.setReceptorName(reception.getUser().getName());

            receptionByDateViews.add(receptionByDateView);
        }
        return receptionByDateViews;
    }
}
