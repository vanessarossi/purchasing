package com.purchasing.support.orderBudget;

import com.purchasing.entity.QuotationRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanessa
 */
public class OrderBudgetViewService {

    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<OrderBudgetViewService> generateList(List<QuotationRequest> quotationRequests) {
        List<OrderBudgetViewService> ordersBudgetViewService = new ArrayList<>();
        for (QuotationRequest quotationRequest : quotationRequests) {
            OrderBudgetViewService orderBudgetViewService = new OrderBudgetViewService();
            orderBudgetViewService.setService(quotationRequest.getSolicitationRequest().getService().getDescription());
            ordersBudgetViewService.add(orderBudgetViewService);
        }
        return ordersBudgetViewService;
    }

}
