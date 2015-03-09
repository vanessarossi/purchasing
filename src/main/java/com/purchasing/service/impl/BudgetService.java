package com.purchasing.service.impl;

import com.purchasing.entity.Budget;
import com.purchasing.entity.PaymentInformation;
import com.purchasing.entity.Solicitation;

/**
 * Created by @vanessa
 */
public interface BudgetService {

    public void saveBudget(Budget budget);
    public Budget searchBudget(Budget budget);

    /** Payment Information **/
    public PaymentInformation findPaymentInformationById(PaymentInformation paymentInformation);

    /**  Status solicitation **/

    public void updateStatusSolicitation(Solicitation solicitation);
}
