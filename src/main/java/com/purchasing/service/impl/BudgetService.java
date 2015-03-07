package com.purchasing.service.impl;

import com.purchasing.entity.Budget;
import com.purchasing.entity.PaymentInformation;

/**
 * Created by @vanessa
 */
public interface BudgetService {

    public void saveBudget(Budget budget);
    public Budget searchBudget(Budget budget);
    public void removeBudget(Budget budget);

    /** Payment Information **/
    public PaymentInformation findPaymentInformationById(PaymentInformation paymentInformation);
}
