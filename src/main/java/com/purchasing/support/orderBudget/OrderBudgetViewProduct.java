package com.purchasing.support.orderBudget;

import com.purchasing.support.quotation.QuotationRequestProductView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanessa
 */
public class OrderBudgetViewProduct {

    private String description;
    private String quantity;
    private String unity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public List<OrderBudgetViewProduct> generateList(List<QuotationRequestProductView> quotationRequestProductViews) {
        List<OrderBudgetViewProduct> ordersBudgetViewProduct= new ArrayList<>();
        for (QuotationRequestProductView quotationRequestProductView : quotationRequestProductViews) {
            OrderBudgetViewProduct orderBudgetViewProduct = new OrderBudgetViewProduct();

            String description = quotationRequestProductView.getProduct().getDescription();
            String model = quotationRequestProductView.getProduct().getModel() == null ? "" : quotationRequestProductView.getProduct().getModel();
            String mark = quotationRequestProductView.getProduct().getMark() == null ? "" : quotationRequestProductView.getProduct().getMark();
            String unity = quotationRequestProductView.getProduct().getUnit().getDescription();

            orderBudgetViewProduct.setDescription(description + " " + model + " " + mark);
            orderBudgetViewProduct.setQuantity(quotationRequestProductView.getQuantity().toString().replace(".",","));
            orderBudgetViewProduct.setUnity(unity);
            ordersBudgetViewProduct.add(orderBudgetViewProduct);
        }
        return ordersBudgetViewProduct;
    }
}
