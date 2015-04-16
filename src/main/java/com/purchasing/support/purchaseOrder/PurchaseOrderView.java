package com.purchasing.support.purchaseOrder;

import com.purchasing.entity.PurchaseOrder;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.PurchaseOrderService;


import java.util.List;

/**
 * Created by Vanessa on 4/16/15.
 */
public class PurchaseOrderView {

    private PurchaseOrder purchaseOrder;
    private List<OrderRequestProductView>orderRequestProductViews;
    private List<OrderRequestServiceView>orderRequestServiceViews;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<OrderRequestProductView> getOrderRequestProductViews() {
        return orderRequestProductViews;
    }

    public void setOrderRequestProductViews(List<OrderRequestProductView> orderRequestProductViews) {
        this.orderRequestProductViews = orderRequestProductViews;
    }

    public List<OrderRequestServiceView> getOrderRequestServiceViews() {
        return orderRequestServiceViews;
    }

    public void setOrderRequestServiceViews(List<OrderRequestServiceView> orderRequestServiceViews) {
        this.orderRequestServiceViews = orderRequestServiceViews;
    }

    public PurchaseOrderView generate(PurchaseOrder purchaseOrder ,PurchaseOrderService purchaseOrderService) {
            PurchaseOrderView purchaseOrderView = new PurchaseOrderView();
            purchaseOrderView.setPurchaseOrder(purchaseOrder);
            if (purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Material)) {
                List<OrderRequestProductView> orderRequestProductViews  = purchaseOrderService.groupByProduct(purchaseOrder);
                purchaseOrderView.setOrderRequestProductViews(orderRequestProductViews);
            } else {
                purchaseOrderView.setOrderRequestServiceViews(new OrderRequestServiceView().generateList(purchaseOrder.getOrderRequests()));
            }
        return purchaseOrderView;
    }
}
