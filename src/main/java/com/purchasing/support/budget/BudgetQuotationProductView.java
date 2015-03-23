package com.purchasing.support.budget;

import com.purchasing.entity.BudgetQuotation;
import com.purchasing.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BudgetQuotationProductView implements Comparator<BudgetQuotationProductView> {

    private Product product;
    private Float quantity;
    private BigDecimal unitaryPrice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(BigDecimal unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public List<BudgetQuotationProductView> generateList(List<BudgetQuotation> budgetQuotations) {
        List<BudgetQuotationProductView> budgetsQuotationProductV = new ArrayList<>();
        for (BudgetQuotation budgetQuotation : budgetQuotations){
            BudgetQuotationProductView budgetQuotationProduct = new BudgetQuotationProductView();
            budgetQuotationProduct.setProduct(budgetQuotation.getQuotationRequest().getSolicitationRequest().getProduct());
            budgetQuotationProduct.setQuantity(budgetQuotation.getQuotationRequest().getSolicitationRequest().getQuantity());
            budgetQuotationProduct.setUnitaryPrice(budgetQuotation.getUnityPrice());
        }
        return  budgetsQuotationProductV;
    }

    @Override
    public int compare(BudgetQuotationProductView o1, BudgetQuotationProductView o2) {
        return 0;
    }
}
