package com.purchasing.support.purchaseOrder;

import com.purchasing.entity.OrderRequest;
import com.purchasing.entity.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Vanessa on 4/16/15.
 */
public class OrderRequestServiceView implements Comparator<OrderRequestServiceView> {

    private Service service;
    private BigDecimal unityPrice;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public BigDecimal getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(BigDecimal unityPrice) {
        this.unityPrice = unityPrice;
    }

    public List<OrderRequestServiceView> generateList(List<OrderRequest> orderRequests) {
        List<OrderRequestServiceView> orderRequestServiceV = new ArrayList<>();
        for (OrderRequest orderRequest : orderRequests) {
            OrderRequestServiceView orderRequestServiceView = new OrderRequestServiceView();
                orderRequestServiceView.setService(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getService());
                orderRequestServiceView.setUnityPrice(orderRequest.getBudgetQuotation().getUnityPrice());
                orderRequestServiceV.add(orderRequestServiceView);
        }
        return orderRequestServiceV;
    }

    @Override
    public int compare(OrderRequestServiceView o1, OrderRequestServiceView o2) {
        return 0;
    }
}
