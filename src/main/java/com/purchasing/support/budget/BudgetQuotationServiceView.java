package com.purchasing.support.budget;

import com.purchasing.entity.BudgetQuotation;
import com.purchasing.entity.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Vanessa on 3/23/15.
 */
public class BudgetQuotationServiceView implements Comparator<BudgetQuotationProductView> {
    private Long id;
    private Service service;
    private BigDecimal unityPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<BudgetQuotationServiceView> generateList(List<BudgetQuotation> budgetQuotations) {
        List<BudgetQuotationServiceView> budgetsQuotationServiceV= new ArrayList<>();
        for (BudgetQuotation budgetQuotation : budgetQuotations) {
            BudgetQuotationServiceView budgetQuotationServiceV = new BudgetQuotationServiceView();
            budgetQuotationServiceV.setId(budgetQuotation.getId());
            budgetQuotationServiceV.setService(budgetQuotation.getQuotationRequest().getSolicitationRequest().getService());
            budgetQuotationServiceV.setUnityPrice(budgetQuotation.getUnityPrice());
            budgetsQuotationServiceV.add(budgetQuotationServiceV);
        }
        return budgetsQuotationServiceV;
    }

    @Override
    public int compare(BudgetQuotationProductView o1, BudgetQuotationProductView o2) {
        return 0;
    }
}
