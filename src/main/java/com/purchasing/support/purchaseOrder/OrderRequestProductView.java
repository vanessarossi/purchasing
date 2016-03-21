package com.purchasing.support.purchaseOrder;

import com.purchasing.entity.OrderRequest;
import com.purchasing.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Vanessa on 4/16/15.
 */
public class OrderRequestProductView implements Comparator<OrderRequestProductView> {

    private Product product;
    private Float quantity;
    private BigDecimal unityPrice;
    private BigDecimal totalPrice;

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

    public List<OrderRequestProductView> generateList(List<OrderRequest> orderRequests) {
        List<OrderRequestProductView> orderRequestProductV = new ArrayList<>();
        for (OrderRequest orderRequest : orderRequests) {
            OrderRequestProductView orderRequestProductView = new OrderRequestProductView();
                orderRequestProductView.setProduct(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getProduct());
                orderRequestProductView.setQuantity(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getQuantity());
                orderRequestProductView.setUnityPrice(orderRequest.getBudgetQuotation().getUnityPrice());
            
            BigDecimal quantity = new BigDecimal(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getQuantity());
            BigDecimal unityPrice =  orderRequest.getBudgetQuotation().getUnityPrice();
            BigDecimal totalPriceFormat = quantity.multiply(unityPrice).setScale(2,BigDecimal.ROUND_HALF_EVEN);

                orderRequestProductView.setTotalPrice(totalPriceFormat);
                orderRequestProductV.add(orderRequestProductView);
        }
        return orderRequestProductV;
    }

    @Override
    public int compare(OrderRequestProductView o1, OrderRequestProductView o2) {
        return 0;
    }
}
