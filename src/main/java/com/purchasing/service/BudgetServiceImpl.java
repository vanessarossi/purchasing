package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.service.impl.BudgetService;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class BudgetServiceImpl implements BudgetService {

    @Inject private BudgetDAO budgetDAO;
    @Inject private BudgetQuotationDAO budgetQuotationDAO;
    @Inject private PaymentInformationDAO paymentInformationDAO;
    @Inject private PaymentInformationBudgetDAO paymentInformationBudgetDAO;
    @Inject private QuotationRequestDAO quotationRequestDAO;

    @Override
    public void saveBudget(Budget budget) {

        Budget budgetSaved = budgetDAO.save(budget);

        for (BudgetQuotation budgetQuotation : budget.getBudgetQuotations()){
            BudgetQuotation newBudgetQuotation = new BudgetQuotation();
                newBudgetQuotation.setBudget(budgetSaved);
                newBudgetQuotation.setQuotationRequest(budgetQuotation.getQuotationRequest());
                newBudgetQuotation.setUnityPrice(budgetQuotation.getUnityPrice());
                newBudgetQuotation.setChosenBudget(false);
            budgetQuotationDAO.save(newBudgetQuotation);

            QuotationRequest quotationRequest = new QuotationRequest();
            quotationRequest = quotationRequestDAO.findById(QuotationRequest.class,quotationRequest.getId());

            updateStatusSolicitation(quotationRequest.getSolicitationRequest().getSolicitation());
        }

        for (PaymentInformationBudget paymentInformationBudget : budget.getPaymentInformationBudgets()){

            PaymentInformation paymentInformation = new PaymentInformation();
                paymentInformation.setContratct(paymentInformationBudget.getPaymentInformation().getContratct());
                paymentInformation.setMeanPayment(paymentInformationBudget.getPaymentInformation().getMeanPayment());
                paymentInformation.setDateFirstInstallment(paymentInformationBudget.getPaymentInformation().getDateFirstInstallment());
                paymentInformation.setDateLastInstallment(paymentInformationBudget.getPaymentInformation().getDateLastInstallment());
                paymentInformation.setDateInput(paymentInformationBudget.getPaymentInformation().getDateInput());
                paymentInformation.setExpirationDate(paymentInformationBudget.getPaymentInformation().getExpirationDate());
                paymentInformation.setInputPrice(paymentInformationBudget.getPaymentInformation().getInputPrice());
                paymentInformation.setSharePrice(paymentInformationBudget.getPaymentInformation().getSharePrice());
                paymentInformation.setTotalPrice(paymentInformationBudget.getPaymentInformation().getTotalPrice());
                paymentInformation.setDiscountPercentage(paymentInformationBudget.getPaymentInformation().getDiscountPercentage());
                paymentInformation.setTotalFinalPrice(paymentInformationBudget.getPaymentInformation().getTotalFinalPrice());
                paymentInformation.setContract(paymentInformationBudget.getPaymentInformation().getContract());
                paymentInformation.setFormPayment(paymentInformationBudget.getPaymentInformation().getFormPayment());

             PaymentInformation paymentInformationSaved = paymentInformationDAO.save(paymentInformation);

            PaymentInformationBudget newPaymentInformationBudget = new PaymentInformationBudget();
                newPaymentInformationBudget.setBudget(budgetSaved);
                newPaymentInformationBudget.setPaymentInformation(paymentInformationSaved);
            paymentInformationBudgetDAO.save(newPaymentInformationBudget);

        }

    }

    @Override
    public Budget searchBudget(Budget budget) {
        return budgetDAO.findById(Budget.class,budget.getId());
    }

    @Override
    public PaymentInformation findPaymentInformationById(PaymentInformation paymentInformation) {
        return paymentInformationDAO.findById(PaymentInformation.class,paymentInformation.getId());
    }

    @Override
    public void updateStatusSolicitation(Solicitation solicitation) {
        solicitation.getSituation().setStatus(StatusEnum.QuotingProcess);
    }
}
