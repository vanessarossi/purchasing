package com.purchasing.service.impl;

import com.purchasing.entity.Budget;
import com.purchasing.entity.PaymentInformation;
import com.purchasing.entity.Solicitation;
import com.purchasing.support.budget.BudgetQuotationProductView;

import java.util.List;

/**
 * Created by @vanessa
 */
public interface BudgetService {

    public Budget saveBudget(Budget budget);
    public Budget findById(Budget budget);

    public List<BudgetQuotationProductView> groupProductBudget(Budget budget);

    /** Payment Information **/
    public PaymentInformation findPaymentInformationById(PaymentInformation paymentInformation);

    /**  Status solicitation **/

    public void updateStatusSolicitation(Solicitation solicitation);
}
