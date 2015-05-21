package com.purchasing.support.purchaseOrder.printer;

import com.purchasing.entity.OrderRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vanessa on 4/23/15.
 */
public class OrderRequestServiceViewPrinter {

    private String service;
    private String unityPrice;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(String unityPrice) {
        this.unityPrice = unityPrice;
    }

    public List<OrderRequestServiceViewPrinter> generateList(List<OrderRequest> orderRequests) {
        List<OrderRequestServiceViewPrinter> orderRequestServiceV = new ArrayList<>();
        for (OrderRequest orderRequest : orderRequests) {
            OrderRequestServiceViewPrinter orderRequestServiceViewPrinter = new OrderRequestServiceViewPrinter();
            orderRequestServiceViewPrinter.setService(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getService().getDescription());
            orderRequestServiceViewPrinter.setUnityPrice(orderRequest.getBudgetQuotation().getUnityPrice().toString().replace(".",","));
            orderRequestServiceV.add(orderRequestServiceViewPrinter);

        }
        return orderRequestServiceV;
    }

}
