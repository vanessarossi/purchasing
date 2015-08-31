package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.BudgetService;
import com.purchasing.support.budget.BudgetQuotationProductView;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author vanessa
 */
public class BudgetServiceImpl implements BudgetService {

    @Inject private BudgetDAO budgetDAO;
    @Inject private BudgetQuotationDAO budgetQuotationDAO;
    @Inject private PaymentInformationDAO paymentInformationDAO;
    @Inject private PaymentInformationBudgetDAO paymentInformationBudgetDAO;
    @Inject private QuotationRequestDAO quotationRequestDAO;
    @Inject private PurchaseOrderDAO purchaseOrderDAO;

    @Override
    public Budget saveBudget(Budget budget) {
        Budget budgetSaved = budgetDAO.save(budget);
        for (BudgetQuotation budgetQuotation : budget.getBudgetQuotations()) {
            if (budgetSaved.getQuotation().getType().equals(TypeEnum.Material)){
                QuotationRequest quotationsRequest = budgetQuotation.getQuotationRequest();
                List<QuotationRequest> quotationRequests = quotationRequestDAO.findQuotationRequestProductByProduct(budget.getQuotation(), quotationsRequest.getSolicitationRequest().getProduct());
                if(budget.getId() != null) {
                    List<BudgetQuotation> budgetQuotations = budgetQuotationDAO.findByBudgetId(budget);
                    for (BudgetQuotation budgetQuotat : budgetQuotations) {
                        if (budgetQuotat.getQuotationRequest().getSolicitationRequest().getProduct().getId().equals(budgetQuotation.getQuotationRequest().getSolicitationRequest().getProduct().getId())) {
                            BudgetQuotation newBudgetQuotation = new BudgetQuotation();
                            newBudgetQuotation.setId(budgetQuotat.getId());
                            newBudgetQuotation.setBudget(budgetSaved);
                            newBudgetQuotation.setQuotationRequest(budgetQuotat.getQuotationRequest());
                            newBudgetQuotation.setUnityPrice(budgetQuotation.getUnityPrice());
                            newBudgetQuotation.setChosenBudget(budgetQuotation.getChosenBudget());
                            budgetQuotationDAO.save(newBudgetQuotation);
                            updateStatusSolicitation(budgetQuotat.getQuotationRequest().getSolicitationRequest().getSolicitation());
                        }
                    }
                }else {
                    for (QuotationRequest quotationRequest : quotationRequests) {
                        if (quotationRequest.getSolicitationRequest().getProduct().getId().equals(quotationsRequest.getSolicitationRequest().getProduct().getId())){
                            BudgetQuotation newBudgetQuotation = new BudgetQuotation();
                            newBudgetQuotation.setBudget(budgetSaved);
                            newBudgetQuotation.setQuotationRequest(quotationRequest);
                            newBudgetQuotation.setUnityPrice(budgetQuotation.getUnityPrice());
                            newBudgetQuotation.setChosenBudget(budgetQuotation.getChosenBudget());

                            budgetQuotationDAO.save(newBudgetQuotation);
                            updateStatusSolicitation(quotationRequest.getSolicitationRequest().getSolicitation());
                        }
                    }
                }
            }else {
                BudgetQuotation newBudgetQuotation = new BudgetQuotation();
                newBudgetQuotation.setId(budgetQuotation.getId());
                newBudgetQuotation.setBudget(budgetSaved);
                newBudgetQuotation.setQuotationRequest(budgetQuotation.getQuotationRequest());
                newBudgetQuotation.setUnityPrice(budgetQuotation.getUnityPrice());
                newBudgetQuotation.setChosenBudget(budgetQuotation.getChosenBudget());
                budgetQuotationDAO.save(newBudgetQuotation);
                QuotationRequest quotationRequest = new QuotationRequest();
                quotationRequest = quotationRequestDAO.findById(QuotationRequest.class, newBudgetQuotation.getQuotationRequest().getId());
                updateStatusSolicitation(quotationRequest.getSolicitationRequest().getSolicitation());
            }
        }
        for (PaymentInformationBudget paymentInformationBudget : budget.getPaymentInformationBudgets()){
            PaymentInformation paymentInformation = new PaymentInformation();
            if (paymentInformationBudget.getPaymentInformation().getHasContract() != null && paymentInformationBudget.getPaymentInformation().getHasContract() != false) {
                paymentInformation.setHasContract(paymentInformationBudget.getPaymentInformation().getHasContract());
                paymentInformation.setContract(paymentInformationBudget.getPaymentInformation().getContract());
            }
            paymentInformation.setMeanPayment(paymentInformationBudget.getPaymentInformation().getMeanPayment());
            paymentInformation.setDateFirstInstallment(paymentInformationBudget.getPaymentInformation().getDateFirstInstallment());
            paymentInformation.setDateLastInstallment(paymentInformationBudget.getPaymentInformation().getDateLastInstallment());
            paymentInformation.setDateInput(paymentInformationBudget.getPaymentInformation().getDateInput());
            paymentInformation.setExpirationDate(paymentInformationBudget.getPaymentInformation().getExpirationDate());
            paymentInformation.setInputPrice(paymentInformationBudget.getPaymentInformation().getInputPrice());
            paymentInformation.setSharePrice(paymentInformationBudget.getPaymentInformation().getSharePrice());
            paymentInformation.setFreight(paymentInformationBudget.getPaymentInformation().getFreight());
            paymentInformation.setTotalPrice(paymentInformationBudget.getPaymentInformation().getTotalPrice());
            paymentInformation.setDiscountPercentage(paymentInformationBudget.getPaymentInformation().getDiscountPercentage());
            paymentInformation.setTotalFinalPrice(paymentInformationBudget.getPaymentInformation().getTotalFinalPrice());
            paymentInformation.setFormPayment(paymentInformationBudget.getPaymentInformation().getFormPayment());
            paymentInformation.setId(paymentInformationBudget.getPaymentInformation().getId());
            PaymentInformation paymentInformationSaved = paymentInformationDAO.save(paymentInformation);

            PaymentInformationBudget newPaymentInformationBudget = new PaymentInformationBudget();
                newPaymentInformationBudget.setId(paymentInformationBudget.getId());
                newPaymentInformationBudget.setBudget(budgetSaved);
                newPaymentInformationBudget.setPaymentInformation(paymentInformationSaved);
            paymentInformationBudgetDAO.save(newPaymentInformationBudget);
        }

        if (budgetSaved.getChosenBudget() != null && budgetSaved.getChosenBudget()  == true){
           List<PurchaseOrder> purchasesOrder = purchaseOrderDAO.findByBudget(budgetSaved);
           if (purchasesOrder.size() == 1){
               PurchaseOrder purchaseOrder = purchasesOrder.get(0);
               PaymentInformation paymentInformation = purchaseOrder.getPaymentInformation();
               paymentInformation.setTotalPrice(budgetSaved.getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice());
           }
        }
        return budgetSaved;
    }

    @Override
    public Budget findById(Budget budget) {
        return budgetDAO.findById(Budget.class,budget.getId());
    }

    @Override
    public List<BudgetQuotationProductView> groupProductBudget(Budget budget) {
        budget = budgetDAO.findById(Budget.class, budget.getId());
        List<BudgetQuotation> budgetQuotations = budget.getBudgetQuotations();
        List<BudgetQuotationProductView> budgetQuotationProductViewList = new ArrayList<>();
        List<BudgetQuotationProductView> budgetQuotationProductViews = new ArrayList<>();

        Collections.sort(budgetQuotationProductViews, new BudgetQuotationProductView());

        Map<Long, BudgetQuotationProductView> map = new HashMap<>();
        budgetQuotationProductViewList = new BudgetQuotationProductView().generateList(budgetQuotations);

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingUsed(false);


        for (BudgetQuotationProductView budgetQuotationProductView : budgetQuotationProductViewList) {
            Long idProduct = budgetQuotationProductView.getProduct().getId();
            if (!map.containsKey(idProduct)) {
                budgetQuotationProductView.setTotalPrice(new BigDecimal(decimalFormat.format(budgetQuotationProductView.getUnityPrice().multiply(new BigDecimal(budgetQuotationProductView.getQuantity())))));
                map.put(budgetQuotationProductView.getProduct().getId(), budgetQuotationProductView);
            } else {
                BudgetQuotationProductView budgetQuotationProductV = map.get(idProduct);
                if (budgetQuotationProductView.getQuantity() == null) {
                    budgetQuotationProductView.setQuantity(0f);
                }if (budgetQuotationProductView.getTotalPrice() == null) {
                    budgetQuotationProductView.setTotalPrice(budgetQuotationProductV.getTotalPrice());
                }
                Float quantity = budgetQuotationProductView.getQuantity() + budgetQuotationProductV.getQuantity();
                BigDecimal totalPrice = budgetQuotationProductV.getTotalPrice().add(budgetQuotationProductView.getUnityPrice().multiply(new BigDecimal(budgetQuotationProductView.getQuantity())));

                budgetQuotationProductV.setQuantity(quantity);
                budgetQuotationProductV.setTotalPrice(new BigDecimal(decimalFormat.format(totalPrice)));
            }
        }

        for (Long key : map.keySet()) {
            BudgetQuotationProductView budgetQuotationProductView = map.get(key);
            budgetQuotationProductViews.add(budgetQuotationProductView);
        }
        return budgetQuotationProductViews;
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
