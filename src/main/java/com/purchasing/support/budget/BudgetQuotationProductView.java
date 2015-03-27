package com.purchasing.support.budget;

import com.purchasing.entity.BudgetQuotation;
import com.purchasing.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BudgetQuotationProductView implements Comparator<BudgetQuotationProductView> {

    private Long id;
    private Product product;
    private Float quantity;
    private BigDecimal unityPrice;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(BigDecimal unityPrice) {
        this.unityPrice = unityPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BudgetQuotationProductView> generateList(List<BudgetQuotation> budgetQuotations) {
        List<BudgetQuotationProductView> budgetsQuotationProductV = new ArrayList<>();
        for (BudgetQuotation budgetQuotation : budgetQuotations){
            BudgetQuotationProductView budgetQuotationProduct = new BudgetQuotationProductView();
            budgetQuotationProduct.setId(budgetQuotation.getId());
            budgetQuotationProduct.setProduct(budgetQuotation.getQuotationRequest().getSolicitationRequest().getProduct());
            budgetQuotationProduct.setQuantity(budgetQuotation.getQuotationRequest().getSolicitationRequest().getQuantity());
            budgetQuotationProduct.setUnityPrice(budgetQuotation.getUnityPrice());
            budgetsQuotationProductV.add(budgetQuotationProduct);
        }
        return  budgetsQuotationProductV;
    }

    @Override
    public int compare(BudgetQuotationProductView o1, BudgetQuotationProductView o2) {
        return 0;
    }
}
