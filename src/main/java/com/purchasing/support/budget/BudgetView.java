package com.purchasing.support.budget;

import com.purchasing.entity.Budget;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.BudgetService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vanessa on 4/6/15.
 */
public class BudgetView {


    private Budget budget;
    private List<BudgetQuotationServiceView> budgetsQuotationService;
    private List<BudgetQuotationProductView> budgetsQuotationProduct;

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<BudgetQuotationServiceView> getBudgetsQuotationService() {
        return budgetsQuotationService;
    }

    public void setBudgetsQuotationService(List<BudgetQuotationServiceView> budgetsQuotationService) {
        this.budgetsQuotationService = budgetsQuotationService;
    }

    public List<BudgetQuotationProductView> getBudgetsQuotationProduct() {
        return budgetsQuotationProduct;
    }

    public void setBudgetsQuotationProduct(List<BudgetQuotationProductView> budgetsQuotationProduct) {
        this.budgetsQuotationProduct = budgetsQuotationProduct;
    }

    public List<BudgetView> generateList(List<Budget> budgets, BudgetService budgetService){
        List<BudgetView>budgetViews = new ArrayList<>();
        for (Budget budgetAux : budgets) {
            BudgetView budgetView = new BudgetView();
            budgetView.setBudget(budgetAux);
            if (budgetAux.getQuotation().getType().equals(TypeEnum.Material)) {
                List<BudgetQuotationProductView> budgetQuotationProductViews =  budgetService.groupProductBudget(budgetAux);
                budgetView.setBudgetsQuotationProduct(budgetQuotationProductViews);
            } else {
                budgetView.setBudgetsQuotationService(new BudgetQuotationServiceView().generateList(budgetAux.getBudgetQuotations()));
            }
            budgetViews.add(budgetView);
        }
        return budgetViews;
    }
}
