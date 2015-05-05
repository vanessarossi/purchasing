package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.MeanPaymentEnum;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.printer.OrderPrinter;
import com.purchasing.printer.PurchaseOrderPrinter;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.support.purchaseOrder.OrderRequestProductView;
import com.purchasing.support.purchaseOrder.printer.PurchaseOrderViewPrinter;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author vanessa
 */
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Inject private HttpSession httpSession;
    @Inject private PurchaseOrderDAO purchaseOrderDAO;
    @Inject private BudgetDAO budgetDAO;
    @Inject private ReceptionDAO receptionDAO;
    @Inject private ApprovalDAO approvalDAO;
    @Inject private OrderRequestDAO orderRequestDAO;
    @Inject private QuotationDAO quotationDAO;
    @Inject private BudgetQuotationDAO budgetQuotationDAO;
    @Inject private SolicitationDAO solicitationDAO;
    @Inject private SituationDAO situationDAO;
    @Inject private PaymentInformationDAO paymentInformationDAO;
    @Inject private FormPaymentDAO formPaymentDAO;
    @Inject private SolicitationRequestDAO solicitationRequestDAO;
    @Inject private DeliveryInformationDAO deliveryInformationDAO;
    @Inject private OrderPrinter orderPrinter;
    @Inject private PurchaseOrderPrinter purchaseOrderPrinter;
    @Inject private RequestDeliveredDAO requestDeliveredDAO;

    @Override
    public PurchaseOrder singleSave(Budget budget) {
        budget = budgetDAO.findById(Budget.class, budget.getId());

        PaymentInformation paymentInformation = new PaymentInformation();
          paymentInformation.setMeanPayment(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getMeanPayment());
          paymentInformation.setFormPayment(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getFormPayment());
          paymentInformation.setTotalPrice(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice());

        paymentInformation = paymentInformationDAO.save(paymentInformation);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setDeliveryInformation(null);
            purchaseOrder.setPaymentInformation(paymentInformation);
            purchaseOrder.setApproval(null);
            purchaseOrder.setBudget(budget);

            purchaseOrder.setDate(new Timestamp(new Date().getTime()));
            purchaseOrder.setStatus(StatusEnum.Open);

        purchaseOrder = purchaseOrderDAO.save(purchaseOrder);

        for (BudgetQuotation budgetQuotation : budget.getBudgetQuotations()) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setPurchaseOrder(purchaseOrder);
            orderRequest.setBudgetQuotation(budgetQuotation);

            orderRequestDAO.save(orderRequest);

            budgetQuotation.setChosenBudget(true);
            budgetQuotationDAO.save(budgetQuotation);

            Situation situation = new Situation();
            situation = budgetQuotation.getQuotationRequest().getSolicitationRequest().getSolicitation().getSituation();
            situation.setStatus(StatusEnum.AnalysisQuote);
            situationDAO.save(situation);
        }

        Quotation quotation = new Quotation();
        quotation = budget.getQuotation();
        quotation.setUser(getUserLogged());
        quotation.setFinalDate(new Timestamp(new Date().getTime()));
        quotation.setStatus(StatusEnum.Finished);
        quotationDAO.save(quotation);

        return purchaseOrder;
    }

    @Override
    public List<PurchaseOrder> variousSave(List<PurchaseOrder> purchaseOrders) {
        List<PurchaseOrder> purchaseOrdersSaved = new ArrayList<>();
        if (purchaseOrders.get(0).getOrderRequests().get(0).getBudgetQuotation().getId() != null){  /** ordem de compra serviço  **/
            purchaseOrdersSaved = saveOrdersService(purchaseOrders);
        }else{  /** ordem de compra produto  **/
            purchaseOrdersSaved = saveOrdersMaterial(purchaseOrders);
        }
        return purchaseOrdersSaved;
    }

    @Override
    public PurchaseOrder singleSaveWithJustification(Budget budget, String justification, Boolean exclusive) {
        budget = budgetDAO.findById(Budget.class, budget.getId());

        PaymentInformation paymentInformation = new PaymentInformation();
        paymentInformation.setMeanPayment(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getMeanPayment());
        paymentInformation.setFormPayment(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getFormPayment());
        paymentInformation.setTotalPrice(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice());

        paymentInformation = paymentInformationDAO.save(paymentInformation);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDeliveryInformation(null);
        purchaseOrder.setPaymentInformation(paymentInformation);
        purchaseOrder.setApproval(null);
        purchaseOrder.setBudget(budget);

        purchaseOrder.setDate(new Timestamp(new Date().getTime()));
        purchaseOrder.setStatus(StatusEnum.Open);

        purchaseOrder = purchaseOrderDAO.save(purchaseOrder);

        for (BudgetQuotation budgetQuotation : budget.getBudgetQuotations()) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setPurchaseOrder(purchaseOrder);
            orderRequest.setBudgetQuotation(budgetQuotation);

            orderRequestDAO.save(orderRequest);

            budgetQuotation.setChosenBudget(true);
            budgetQuotationDAO.save(budgetQuotation);

            Situation situation = new Situation();
            situation = budgetQuotation.getQuotationRequest().getSolicitationRequest().getSolicitation().getSituation();
            situation.setStatus(StatusEnum.AnalysisQuote);
            situationDAO.save(situation);
        }

        Quotation quotation = new Quotation();
        quotation = budget.getQuotation();
        quotation.setUser(getUserLogged());
        quotation.setJustification(justification);
        quotation.setExclusiveSupplier(exclusive);
        quotation.setFinalDate(new Timestamp(new Date().getTime()));
        quotation.setStatus(StatusEnum.Finished);
        quotationDAO.save(quotation);

        return purchaseOrder;
    }

    @Override
    public void saveDeliveryAndPayment(PurchaseOrder purchaseOrder) {
        DeliveryInformation deliveryInformation = deliveryInformationDAO.save(purchaseOrder.getDeliveryInformation());

        PaymentInformation paymentInformation = new PaymentInformation();
        if (purchaseOrder.getPaymentInformation().getHasContract() != null) {
            paymentInformation.setHasContract(purchaseOrder.getPaymentInformation().getHasContract());
            paymentInformation.setContract(purchaseOrder.getPaymentInformation().getContract());
        }
        paymentInformation.setMeanPayment(purchaseOrder.getPaymentInformation().getMeanPayment());
        paymentInformation.setDateFirstInstallment(purchaseOrder.getPaymentInformation().getDateFirstInstallment());
        paymentInformation.setDateLastInstallment(purchaseOrder.getPaymentInformation().getDateLastInstallment());
        paymentInformation.setDateInput(purchaseOrder.getPaymentInformation().getDateInput());
        paymentInformation.setExpirationDate(purchaseOrder.getPaymentInformation().getExpirationDate());
        paymentInformation.setInputPrice(purchaseOrder.getPaymentInformation().getInputPrice());
        paymentInformation.setSharePrice(purchaseOrder.getPaymentInformation().getSharePrice());
        paymentInformation.setTotalPrice(purchaseOrder.getPaymentInformation().getTotalPrice());
        paymentInformation.setDiscountPercentage(purchaseOrder.getPaymentInformation().getDiscountPercentage());
        paymentInformation.setTotalFinalPrice(purchaseOrder.getPaymentInformation().getTotalFinalPrice());
        paymentInformation.setFormPayment(purchaseOrder.getPaymentInformation().getFormPayment());
        paymentInformation.setId(purchaseOrder.getPaymentInformation().getId());
        paymentInformation = paymentInformationDAO.save(paymentInformation);

        Boolean alreadyPurchased = purchaseOrder.getAlreadyPurchased();

        purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class, purchaseOrder.getId());

        purchaseOrder.setDeliveryInformation(deliveryInformation);
        purchaseOrder.setPaymentInformation(paymentInformation);
        purchaseOrder.setAlreadyPurchased(alreadyPurchased);

        if (purchaseOrder.getStatus().equals(StatusEnum.Approved)){
            purchaseOrder.setStatus(StatusEnum.BuyingProcess);
            for (OrderRequest orderRequest: purchaseOrder.getOrderRequests()){
                alterStausSolicitationBuyingProcess(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation());
            }
        }
        purchaseOrderDAO.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder findById(PurchaseOrder purchaseOrder) {
        PurchaseOrder purchaseOrderFound = new PurchaseOrder();
        if (purchaseOrder.getId() != null){
            purchaseOrderFound = purchaseOrderDAO.findById(PurchaseOrder.class, purchaseOrder.getId());
        }
        return purchaseOrderFound;
    }

    @Override
    public List<PurchaseOrder> findBySupplierOrderDate(Supplier supplier) {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        if (supplier.getId() != null){
            purchaseOrders = purchaseOrderDAO.findBySupplierOrderDate(supplier);
        }
        return purchaseOrders;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<PurchaseOrder> purchaseOrders = purchaseOrderDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> purchaseOrderList = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            String colCode = purchaseOrder.getId().toString();
            String colSupplier = purchaseOrder.getBudget().getSupplier().getPerson().getName();
            String colStatus = purchaseOrder.getStatus().getDescription();
            String colButtonEdit = "<a href=/purchasing/ordemCompra/adicionar/informacao/" + purchaseOrder.getId() +"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String colButtonView = "<a href=/purchasing/ordemCompra/visualizar/" + purchaseOrder.getId() +"/normal><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";

            String[] row = {
                    colCode,
                    colSupplier,
                    colStatus,
                    colButtonEdit,
                    colButtonView,
            };
            purchaseOrderList.add(row);
        }
        return purchaseOrderList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        return purchaseOrderDAO.totalPagination(search);
    }

    @Override
    public List<Object[]> findPaginationWithFilter(String sSearch, StatusEnum status, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<PurchaseOrder> purchaseOrders = purchaseOrderDAO.paginationWithFilter(search,status,iDisplayStart,iDisplayLength);
        List<Object[]> purchaseOrderList = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            String colCode = purchaseOrder.getId().toString();
            String colSupplier = purchaseOrder.getBudget().getSupplier().getPerson().getName();
            String colStatus = purchaseOrder.getStatus().getDescription();
            String colButtonEdit = "<a href=/purchasing/ordemCompra/adicionar/informacao/" + purchaseOrder.getId() +"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String colButtonView = "<a href=/purchasing/ordemCompra/visualizar/" + purchaseOrder.getId() +"/normal><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";

            String[] row = {
                    colCode,
                    colSupplier,
                    colStatus,
                    colButtonEdit,
                    colButtonView,
            };
            purchaseOrderList.add(row);
        }
        return purchaseOrderList;
    }

    @Override
    public Integer totalPaginationWithFilter(String sSearch, StatusEnum status) {
        String search = sSearch == null ? "" : sSearch;
        return purchaseOrderDAO.totalPaginationWithFilter(search,status);
    }

    @Override
    public List<Object[]> findPaginationMissing(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        switch (getUserLogged().getRole().getId().intValue()){
            case 2:
                purchaseOrders = purchaseOrderDAO.paginationMissingDirectorship(search, getUserLogged().getRole().getMinimumValue(), iDisplayStart, iDisplayLength);
                break;
            case 3:
                purchaseOrders = purchaseOrderDAO.paginationMissingDirector(search, getUserLogged().getRole().getMinimumValue(), iDisplayStart, iDisplayLength);
                break;
            case 4:
                purchaseOrders = purchaseOrderDAO.paginationMissingManager(search, getUserLogged().getRole().getMinimumValue(), iDisplayStart, iDisplayLength);
                break;
            case 5:
                purchaseOrders = purchaseOrderDAO.paginationMissingAnalyst(search, iDisplayStart, iDisplayLength);
                break;
        }
        List<Object[]> purchaseOrderList = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            String colCode = purchaseOrder.getId().toString();
            String colSupplier = purchaseOrder.getBudget().getSupplier().getPerson().getName();
            String colButtonView = "<a href=/purchasing/ordemCompra/visualizar/" + purchaseOrder.getId() +"/approve><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";

            String[] row = {
                    colCode,
                    colSupplier,
                    colButtonView,
            };
            purchaseOrderList.add(row);
        }
        return purchaseOrderList;
    }

    @Override
    public Integer totalPaginationMissing(String sSearch) {
        Integer total = 0;
        String search = sSearch == null ? "" : sSearch;
        switch (getUserLogged().getId().intValue()) {
            case 2:
                total = purchaseOrderDAO.totalPaginationMissingDirectorship(search, getUserLogged().getRole().getMinimumValue());
                break;
            case 3:
                total = purchaseOrderDAO.totalPaginationMissingDirector(search, getUserLogged().getRole().getMinimumValue());
                break;
            case 4:
                total = purchaseOrderDAO.totalPaginationMissingManager(search, getUserLogged().getRole().getMinimumValue());
                break;
            case 5:
                total = purchaseOrderDAO.totalPaginationMissingAnalyst(search);
                break;
        }

        return total;
    }

    @Override
    public List<Object[]> findPaginationMissingConfered(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Reception> receptions = new ArrayList<>();
        receptions = receptionDAO.paginationMissingConfered(search,iDisplayStart,iDisplayLength);

        List<Object[]> purchaseOrderList = new ArrayList<>();

        for (Reception reception : receptions) {
            String colCode = reception.getPurchaseOrder().getId().toString();
            String colSupplier = reception.getPurchaseOrder().getBudget().getSupplier().getPerson().getName();
            String colButtonConfirmConference = "<a href=/purchasing/ordemCompra/confirmacao/conferencia/" + reception.getId() +"><span class=\"fa fa-check-square btn btn-default btn-xs\"></span></a>";

            String[] row = {
                    colCode,
                    colSupplier,
                    colButtonConfirmConference,
            };
            purchaseOrderList.add(row);
        }
        return purchaseOrderList;
    }

    @Override
    public Integer totalPaginationMissingConfered(String sSearch) {
        Integer total = 0;
        String search = sSearch == null ? "" : sSearch;
        total = receptionDAO.totalPaginationMissingConfered(search);
        return total;
    }

    @Override
    public PurchaseOrder findByConference(Long id) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
            if (id != null){
                purchaseOrder = purchaseOrderDAO.findByConference(id);
            }
        return purchaseOrder;
    }

    @Override
    public List<OrderRequestProductView> groupByProduct(PurchaseOrder purchaseOrder) {
        purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class,purchaseOrder.getId());

        List<OrderRequest> orderRequests = purchaseOrder.getOrderRequests();
        List<OrderRequestProductView> orderRequestProductViewList = new ArrayList<>();
        List<OrderRequestProductView> orderRequestProductViews = new ArrayList<>();

        Collections.sort(orderRequestProductViews,new OrderRequestProductView());
        Map<Long,OrderRequestProductView> map = new HashMap<>();
        orderRequestProductViewList = new OrderRequestProductView().generateList(orderRequests);

        for (OrderRequestProductView orderRequestProductView : orderRequestProductViewList){
            Long idProduct = orderRequestProductView.getProduct().getId();
            if (!map.containsKey(idProduct)){
                orderRequestProductView.setTotalPrice(orderRequestProductView.getTotalPrice());
                map.put(orderRequestProductView.getProduct().getId(),orderRequestProductView);
            }else{
                OrderRequestProductView orderRequestProductV = map.get(idProduct);
                if (orderRequestProductView.getQuantity() == null){
                    orderRequestProductView.setQuantity(0f);
                }
                if (orderRequestProductView.getTotalPrice() == null){
                    orderRequestProductView.setTotalPrice(orderRequestProductV.getTotalPrice());
                }
                Float quantity = orderRequestProductView.getQuantity() + orderRequestProductV.getQuantity();
                BigDecimal totalPrice = orderRequestProductV.getTotalPrice().add(orderRequestProductView.getTotalPrice());
                orderRequestProductV.setQuantity(quantity);
                orderRequestProductV.setTotalPrice(totalPrice);
            }
        }
        for (Long key : map.keySet()){
            OrderRequestProductView orderRequestProductView = map.get(key);
            orderRequestProductViews.add(orderRequestProductView);
        }
        return orderRequestProductViews;
    }

    @Override
    public List<PurchaseOrderViewPrinter> groupByCostCenter(List<PurchaseOrderViewPrinter> purchaseOrderViewPrinters) {
        List<PurchaseOrderViewPrinter> purchaseOrderViewPrinterArrayList = new ArrayList<>();
        List<PurchaseOrderViewPrinter> purchaseOrderViewPrinterList= new ArrayList<>();

        Collections.sort(purchaseOrderViewPrinterList,new PurchaseOrderViewPrinter());
        Map<String,PurchaseOrderViewPrinter> map = new HashMap<>();

        purchaseOrderViewPrinterArrayList = purchaseOrderViewPrinters;
        for (PurchaseOrderViewPrinter purchaseOrderViewPrinter : purchaseOrderViewPrinterArrayList){
            String idCostCenter = purchaseOrderViewPrinter.getCode_cost_center();
            if (!map.containsKey(idCostCenter)){
                map.put(purchaseOrderViewPrinter.getCode_cost_center(),purchaseOrderViewPrinter);
            }else{
                PurchaseOrderViewPrinter purchaseOrderV = map.get(idCostCenter);
                BigDecimal totalPrice = new BigDecimal(purchaseOrderV.getTotal_price().replace(",",".")).add(new BigDecimal(purchaseOrderViewPrinter.getTotal_price().replace(",", ".")));
                purchaseOrderV.setTotal_price(totalPrice.toString().replace(".", ","));
            }
        }
        for (String key : map.keySet()){
            PurchaseOrderViewPrinter purchaseOrderViewPrinter = map.get(key);
            purchaseOrderViewPrinterList.add(purchaseOrderViewPrinter);
        }
        return purchaseOrderViewPrinterList;
    }

    @Override
    public void approve(PurchaseOrder purchaseOrder) {
        purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class,purchaseOrder.getId());
        Approval approval = new Approval();
        if (purchaseOrder.getApproval() != null) {
            approval = purchaseOrder.getApproval();
        }

        Integer compareMinimum = purchaseOrder.getPaymentInformation().getTotalPrice().compareTo(getUserLogged().getRole().getMinimumValue());
        Integer compareMaximum = purchaseOrder.getPaymentInformation().getTotalPrice().compareTo(getUserLogged().getRole().getMaximumValue());
            switch (getUserLogged().getRole().getId().intValue()) {
            case 2: /** Conselho **/
                approval.setFourthApproval(true);
                approval.setDateFourthApproval(new Timestamp(new Date().getTime()));
                approval.setUserFourthApproval(getUserLogged().getName());
                approval = approvalDAO.save(approval);

                for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                    SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                    solicitationRequest.setStatus(StatusEnum.Approved);
                    solicitationRequestDAO.save(solicitationRequest);
                    alterStatusSolicitationApproved(solicitationRequest.getSolicitation());
                }
                purchaseOrder.setApproval(approval);

                if (compareMinimum == 1 || compareMinimum == 0) {
                    purchaseOrder.setStatus(StatusEnum.Approved);
                }
                purchaseOrderDAO.save(purchaseOrder);
                break;
            case 3: /** Diretor  **/
                approval.setThirdApproval(true);
                approval.setDateThirdApproval(new Timestamp(new Date().getTime()));
                approval.setUserThirdApproval(getUserLogged().getName());
                approval = approvalDAO.save(approval);

                for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                    SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                    solicitationRequest.setStatus(StatusEnum.Approved);
                    solicitationRequestDAO.save(solicitationRequest);
                    alterStatusSolicitationApproved(solicitationRequest.getSolicitation());
                }

                purchaseOrder.setApproval(approval);
                if ((compareMinimum == 1 || compareMinimum == 0) && (compareMaximum == -1 || compareMaximum == 0)) {
                    purchaseOrder.setStatus(StatusEnum.Approved);
                }

                purchaseOrderDAO.save(purchaseOrder);
                break;
            case 4: /** Gerente **/
                approval.setSecondApproval(true);
                approval.setDateSecondApproval(new Timestamp(new Date().getTime()));
                approval.setUserSecondApproval(getUserLogged().getName());
                approval = approvalDAO.save(approval);

                for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                    SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                    solicitationRequest.setStatus(StatusEnum.Approved);
                    solicitationRequestDAO.save(solicitationRequest);
                    alterStatusSolicitationApproved(solicitationRequest.getSolicitation());
                }
                purchaseOrder.setApproval(approval);

                if ((compareMinimum == 1 || compareMinimum == 0) && (compareMaximum == -1 || compareMaximum == 0)) {
                    purchaseOrder.setStatus(StatusEnum.Approved);
                }
                purchaseOrderDAO.save(purchaseOrder);
                break;
            case 5: /** Analista **/
                    approval.setFirstApproval(true);
                    approval.setDateFirstApproval(new Timestamp(new Date().getTime()));
                    approval.setUserFirstApproval(getUserLogged().getName());
                    approval = approvalDAO.save(approval);

                for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                    SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                    solicitationRequest.setStatus(StatusEnum.Approved);
                    solicitationRequestDAO.save(solicitationRequest);
                    alterStatusSolicitationApproved(solicitationRequest.getSolicitation());
                }
                purchaseOrder.setApproval(approval);

                if ((compareMinimum == 1 || compareMinimum == 0 )&& (compareMaximum == -1 || compareMaximum == 0)) {
                    purchaseOrder.setStatus(StatusEnum.Approved);
                }
                purchaseOrderDAO.save(purchaseOrder);
                break;
        }
    }

    @Override
    public void reprove(PurchaseOrder purchaseOrder,String justification) {
        purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class, purchaseOrder.getId());
        Approval approval = new Approval();
        if (purchaseOrder.getApproval() != null) {
            approval = purchaseOrder.getApproval();
        }
        switch (getUserLogged().getRole().getId().intValue()) {
            case 2: /** Conselho **/
                approval.setFourthApproval(false);
                approval.setDateFourthApproval(new Timestamp(new Date().getTime()));
                approval.setUserFourthApproval(getUserLogged().getName());
                approval.setJustificationDisapproval(justification);
                approval = approvalDAO.save(approval);
                purchaseOrder.setStatus(StatusEnum.Reject);
                purchaseOrder.setApproval(approval);
                purchaseOrderDAO.save(purchaseOrder);
                for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                    SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                    solicitationRequest.setStatus(StatusEnum.Reproved);
                    solicitationRequestDAO.save(solicitationRequest);
                    alterStatusSolicitationReject(solicitationRequest.getSolicitation());
                }
                break;
            case 3: /** Diretor  **/
                approval.setThirdApproval(false);
                approval.setDateThirdApproval(new Timestamp(new Date().getTime()));
                approval.setUserThirdApproval(getUserLogged().getName());
                approval.setJustificationDisapproval(justification);
                approval = approvalDAO.save(approval);
                    purchaseOrder.setStatus(StatusEnum.Reject);
                    purchaseOrder.setApproval(approval);
                    purchaseOrderDAO.save(purchaseOrder);
                    for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                        SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                        solicitationRequest.setStatus(StatusEnum.Reproved);
                        solicitationRequestDAO.save(solicitationRequest);
                        alterStatusSolicitationReject(solicitationRequest.getSolicitation());
                    }
                break;
            case 4: /** Gerente **/
                approval.setSecondApproval(true);
                approval.setDateSecondApproval(new Timestamp(new Date().getTime()));
                approval.setUserSecondApproval(getUserLogged().getName());
                approval.setJustificationDisapproval(justification);
                approval = approvalDAO.save(approval);
                    purchaseOrder.setStatus(StatusEnum.Reject);
                    purchaseOrder.setApproval(approval);
                    purchaseOrderDAO.save(purchaseOrder);
                    purchaseOrder.setApproval(approval);
                    for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                        SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                        solicitationRequest.setStatus(StatusEnum.Reproved);
                        solicitationRequestDAO.save(solicitationRequest);
                        alterStatusSolicitationReject(solicitationRequest.getSolicitation());
                    }
                break;
            case 5: /** Analista **/
                approval.setFirstApproval(true);
                approval.setDateFirstApproval(new Timestamp(new Date().getTime()));
                approval.setUserFirstApproval(getUserLogged().getName());
                approval.setJustificationDisapproval(justification);
                approval = approvalDAO.save(approval);
                purchaseOrder.setStatus(StatusEnum.Reject);
                purchaseOrder.setApproval(approval);
                    purchaseOrderDAO.save(purchaseOrder);
                for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                    SolicitationRequest solicitationRequest = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
                    solicitationRequest.setStatus(StatusEnum.Reproved);
                    solicitationRequestDAO.save(solicitationRequest);
                    alterStatusSolicitationReject(solicitationRequest.getSolicitation());
                }
                break;
        }
    }

    @Override
    public File printerOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class, purchaseOrder.getId());
        if(purchaseOrder.getDateGenerate() == null){
            purchaseOrder.setDateGenerate(new Timestamp(new Date().getTime()));
            purchaseOrderDAO.save(purchaseOrder);
        }
        if (purchaseOrder.getStatus() == StatusEnum.BuyingProcess){
            purchaseOrder.setStatus(StatusEnum.PurchaseMade);
            purchaseOrderDAO.save(purchaseOrder);
            for (OrderRequest orderRequest : purchaseOrder.getOrderRequests()) {
                alterStausSolicitationPurchaseMade(orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation());
            }
        }

        return orderPrinter.generateOrder(purchaseOrder.getId(), this);
    }

    @Override
    public File printerPurchaseOrder(Reception reception) {
        reception = receptionDAO.findById(Reception.class, reception.getId());
        return purchaseOrderPrinter.generatePurchaseOrder(reception.getPurchaseOrder(), reception, this);
    }

    @Override
    public Float getQuantityByOrderRequest(OrderRequest orderRequest) {
        orderRequest = orderRequestDAO.findById(OrderRequest.class,orderRequest.getId());
        Float total = orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getQuantity() == null ? 1f : orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getQuantity();
        return total;
    }

    @Override
    public Float getQuantityDeliveredByOrderRequest(OrderRequest orderRequest) {
        return requestDeliveredDAO.totalDeliveredByOrderRequest(orderRequest);
    }

    @Override
    public void saveReception(Reception reception,StatusEnum statusEnum) {
        List<RequestDelivered> requestDelivereds = reception.getRequestDelivereds();
        PurchaseOrder purchaseOrder = purchaseOrderDAO.findById(PurchaseOrder.class, reception.getPurchaseOrder().getId());

        if (statusEnum == StatusEnum.Conferred){
            reception.setDate(new Timestamp(new Date().getTime()));
            reception.setUser(getUserLogged());
            reception.setStatus(statusEnum);
            reception.setPaymentInformation(null);
            reception = receptionDAO.save(reception);

            for (RequestDelivered requestDelivered : requestDelivereds){
                if ((purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Material) && requestDelivered.getQuantity() != null && requestDelivered.getQuantity() > 0f)||(purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Service))){
                    requestDelivered.setReception(reception);
                    RequestDelivered requestDeliveredSaved  = requestDeliveredDAO.save(requestDelivered);
                    alterStatusSolicitationConfered(requestDeliveredSaved.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation());
                }
            }
            purchaseOrder.setStatus(StatusEnum.Conferred);
            purchaseOrderDAO.save(purchaseOrder);
        }else{
            reception.setDate(new Timestamp(new Date().getTime()));
            reception.setUser(getUserLogged());
            reception.setStatus(statusEnum);
            reception.setPaymentInformation(purchaseOrder.getPaymentInformation());
            reception = receptionDAO.save(reception);

            for (RequestDelivered requestDelivered : requestDelivereds) {
                if ((purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Material) && requestDelivered.getQuantity() != null && requestDelivered.getQuantity() > 0f)||(purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Service))) {
                    requestDelivered.setReception(reception);
                    RequestDelivered requestDeliveredSaved = requestDeliveredDAO.save(requestDelivered);
                    alterStatusProduct(requestDelivered.getOrderRequest());
                    alterStatusFinishedOrPartiallyFinished(requestDeliveredSaved.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation());
                }
            }
           alterStatusPurchaseOrder(purchaseOrder);
            purchaseOrderDAO.save(purchaseOrder);
        }
    }

    @Override
    public void saveConference(Reception reception) {
        PaymentInformation paymentInformation = new PaymentInformation();
        if (reception.getPaymentInformation().getHasContract() != null) {
            paymentInformation.setHasContract(reception.getPaymentInformation().getHasContract());
            paymentInformation.setContract(reception.getPaymentInformation().getContract());
        }
        paymentInformation.setMeanPayment(reception.getPaymentInformation().getMeanPayment());
        paymentInformation.setDateFirstInstallment(reception.getPaymentInformation().getDateFirstInstallment());
        paymentInformation.setDateLastInstallment(reception.getPaymentInformation().getDateLastInstallment());
        paymentInformation.setDateInput(reception.getPaymentInformation().getDateInput());
        paymentInformation.setExpirationDate(reception.getPaymentInformation().getExpirationDate());
        paymentInformation.setInputPrice(reception.getPaymentInformation().getInputPrice());
        paymentInformation.setSharePrice(reception.getPaymentInformation().getSharePrice());
        paymentInformation.setTotalPrice(reception.getPaymentInformation().getTotalPrice());
        paymentInformation.setDiscountPercentage(reception.getPaymentInformation().getDiscountPercentage());
        paymentInformation.setTotalFinalPrice(reception.getPaymentInformation().getTotalFinalPrice());
        paymentInformation.setFormPayment(reception.getPaymentInformation().getFormPayment());
        paymentInformation.setId(reception.getPaymentInformation().getId());

        paymentInformation = paymentInformationDAO.save(paymentInformation);

        reception = receptionDAO.findById(Reception.class,reception.getId());
        reception.setStatus(StatusEnum.Finished);
        reception.setPaymentInformation(paymentInformation);

        receptionDAO.save(reception);

        PurchaseOrder purchaseOrder = reception.getPurchaseOrder();

        for (RequestDelivered requestDelivered : reception.getRequestDelivereds()) {
            requestDelivered.setReception(reception);
            RequestDelivered requestDeliveredSaved = requestDeliveredDAO.save(requestDelivered);
            alterStatusProduct(requestDelivered.getOrderRequest());
            alterStatusFinishedOrPartiallyFinished(requestDeliveredSaved.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation());
        }

        alterStatusPurchaseOrder(purchaseOrder);
        purchaseOrderDAO.save(purchaseOrder);
    }

    @Override
    public Reception findReceptionById(Reception reception) {
        return receptionDAO.findById(Reception.class,reception.getId());
    }

    @Override
    public BigDecimal sumTotal(List<RequestDelivered> requestDelivereds) {
        BigDecimal total = new BigDecimal("0");

        for (RequestDelivered requestDelivered : requestDelivereds){
            BigDecimal quantity = requestDelivered.getQuantity() == null ? new BigDecimal("1") : new BigDecimal(requestDelivered.getQuantity());
            BigDecimal unitPrice = requestDelivered.getOrderRequest().getBudgetQuotation().getUnityPrice();
            BigDecimal totalRequest = quantity.multiply(unitPrice);
            total = total.add(totalRequest);
        }
        return total;
    }


    /** utilizados para ajudar   **/
    public User getUserLogged() {
        User user = (User) httpSession.getAttribute("userLogged");
        return user;
    }

    public List<PurchaseOrder> saveOrdersService(List<PurchaseOrder> purchaseOrders){
        List<PurchaseOrder> purchaseOrdersSaved = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            List<OrderRequest> orderRequests = purchaseOrder.getOrderRequests();

            PaymentInformation paymentInformation = new PaymentInformation();
            paymentInformation.setMeanPayment(MeanPaymentEnum.Money);
            paymentInformation.setFormPayment(formPaymentDAO.findById(FormPayment.class, 1l));
            paymentInformation.setTotalPrice(purchaseOrder.getPaymentInformation().getTotalPrice());

            paymentInformation = paymentInformationDAO.save(paymentInformation);

            purchaseOrder.setDeliveryInformation(null);
            purchaseOrder.setPaymentInformation(paymentInformation);
            purchaseOrder.setApproval(null);
            purchaseOrder.setBudget(purchaseOrder.getOrderRequests().get(0).getBudgetQuotation().getBudget());

            purchaseOrder.setDate(new Timestamp(new Date().getTime()));
            purchaseOrder.setStatus(StatusEnum.Open);

            purchaseOrder = purchaseOrderDAO.save(purchaseOrder);
            Quotation quotation = new Quotation();
            for (OrderRequest orderRequest : orderRequests) {
                if (orderRequest.getBudgetQuotation().getChosenBudget() != null && orderRequest.getBudgetQuotation().getChosenBudget() == true) {
                    BudgetQuotation budgetQuotationFound = budgetQuotationDAO.findById(BudgetQuotation.class, orderRequest.getBudgetQuotation().getId());

                    OrderRequest newOrderRequest = new OrderRequest();
                    newOrderRequest.setBudgetQuotation(budgetQuotationFound);
                    newOrderRequest.setPurchaseOrder(purchaseOrder);

                    orderRequestDAO.save(newOrderRequest);

                    budgetQuotationFound.setChosenBudget(true);
                    budgetQuotationDAO.save(budgetQuotationFound);

                    Situation situation = budgetQuotationFound.getQuotationRequest().getSolicitationRequest().getSolicitation().getSituation();
                    situation.setStatus(StatusEnum.AnalysisQuote);
                    situationDAO.save(situation);

                    quotation = budgetQuotationFound.getQuotationRequest().getQuotation();
                }

            }

            quotation.setUser(getUserLogged());
            quotation.setFinalDate(new Timestamp(new Date().getTime()));
            quotation.setStatus(StatusEnum.Finished);
            quotationDAO.save(quotation);

            purchaseOrdersSaved.add(purchaseOrder);
        }
        return  purchaseOrdersSaved;
    }

    public List<PurchaseOrder> saveOrdersMaterial(List<PurchaseOrder> purchaseOrders) {
        List<PurchaseOrder> purchaseOrdersSaved = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            List<OrderRequest> orderRequests = purchaseOrder.getOrderRequests();

            PaymentInformation paymentInformation = new PaymentInformation();
            paymentInformation.setMeanPayment(MeanPaymentEnum.Money);
            paymentInformation.setFormPayment(formPaymentDAO.findById(FormPayment.class, 1l));
            paymentInformation.setTotalPrice(purchaseOrder.getPaymentInformation().getTotalPrice());

            paymentInformation = paymentInformationDAO.save(paymentInformation);

            purchaseOrder.setDeliveryInformation(null);
            purchaseOrder.setPaymentInformation(paymentInformation);
            purchaseOrder.setApproval(null);
            purchaseOrder.setBudget(purchaseOrder.getOrderRequests().get(0).getBudgetQuotation().getBudget());

            purchaseOrder.setDate(new Timestamp(new Date().getTime()));
            purchaseOrder.setStatus(StatusEnum.Open);

            purchaseOrder = purchaseOrderDAO.save(purchaseOrder);
            Quotation quotation = new Quotation();
            for (OrderRequest orderRequest : orderRequests) {
                if (orderRequest.getBudgetQuotation().getChosenBudget()!= null && orderRequest.getBudgetQuotation().getChosenBudget() == true) {
                    List<BudgetQuotation> budgetQuotations = budgetQuotationDAO.findByBudgetAndProduct(orderRequest.getBudgetQuotation().getBudget(), orderRequest.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getProduct());
                    for (BudgetQuotation budgetQuotation : budgetQuotations) {
                        OrderRequest newOrderRequest = new OrderRequest();
                        newOrderRequest.setBudgetQuotation(budgetQuotation);
                        newOrderRequest.setPurchaseOrder(purchaseOrder);

                        orderRequestDAO.save(newOrderRequest);

                        budgetQuotation.setChosenBudget(true);
                        budgetQuotationDAO.save(budgetQuotation);

                        Situation situation = budgetQuotation.getQuotationRequest().getSolicitationRequest().getSolicitation().getSituation();
                        situation.setStatus(StatusEnum.AnalysisQuote);
                        situationDAO.save(situation);
                    }
                    quotation = budgetQuotations.get(0).getQuotationRequest().getQuotation();
                }

            }
            quotation.setUser(getUserLogged());
            quotation.setFinalDate(new Timestamp(new Date().getTime()));
            quotation.setStatus(StatusEnum.Finished);
            quotationDAO.save(quotation);

            purchaseOrdersSaved.add(purchaseOrder);
        }
        return  purchaseOrdersSaved;
    }

    public void alterStatusSolicitationApproved(Solicitation solicitation){
        Integer totalApproved = solicitationRequestDAO.totalSolicitationRequestApprovedBySolicitation(solicitation);
        Integer total = solicitationRequestDAO.totalSolicitationRequestBySolicitation(solicitation);
        Situation situation = solicitation.getSituation();
        if (totalApproved == total){
            situation.setStatus(StatusEnum.QuoteApproved);
            situationDAO.save(situation);
        }else {
            situation.setStatus(StatusEnum.PartiallyQuoteApproved);
            situationDAO.save(situation);
        }
    }

    public void alterStatusSolicitationReject(Solicitation solicitation) {
        Integer totalReproved = solicitationRequestDAO.totalSolicitationRequestReproveBySolicitation(solicitation);
        Integer total = solicitationRequestDAO.totalSolicitationRequestBySolicitation(solicitation);
        Situation situation = solicitation.getSituation();
        if (totalReproved == total) {
            situation.setStatus(StatusEnum.QuoteReject);
            situationDAO.save(situation);
        }
    }

    public void alterStausSolicitationBuyingProcess(Solicitation solicitation){
        Situation situation = solicitation.getSituation();
        situation.setStatus(StatusEnum.BuyingProcess);
        situationDAO.save(situation);
    }

    public void alterStausSolicitationPurchaseMade(Solicitation solicitation) {
        Situation situation = solicitation.getSituation();
        situation.setStatus(StatusEnum.PurchaseMade);
        situationDAO.save(situation);
    }

    public  void alterStatusSolicitationConfered(Solicitation solicitation){
        Situation situation = solicitation.getSituation();
        situation.setStatus(StatusEnum.Conferred);
        situationDAO.save(situation);
    }

    public void alterStatusPurchaseOrder(PurchaseOrder purchaseOrder){
        List<OrderRequest> orderRequests = purchaseOrder.getOrderRequests();
        Float receivable = 0f;
        StatusEnum statusEnum = StatusEnum.Finished;
        for (OrderRequest orderRequest : orderRequests){
            Float quantity = getQuantityByOrderRequest(orderRequest);
            Float quantityDelivered = getQuantityDeliveredByOrderRequest(orderRequest);
            receivable = quantity - quantityDelivered;
            if (receivable > 0 ){
                statusEnum = statusEnum.PartiallyFinished;
            }
        }
        purchaseOrder.setStatus(statusEnum);
    }

    public void alterStatusFinishedOrPartiallyFinished(Solicitation solicitation){
        Situation situation = solicitation.getSituation();

        Integer totalDelivered = solicitationRequestDAO.totalSolicitationRequestDeliveredBySolicitation(solicitation);
        Integer total = solicitationRequestDAO.totalSolicitationRequestBySolicitation(solicitation);

        if (total == totalDelivered){
            situation.setStatus(StatusEnum.Finished);
        }else{
            situation.setStatus(StatusEnum.PartiallyFinished);
        }
        situationDAO.save(situation);

    }

    public void alterStatusProduct(OrderRequest orderRequest){
        Float total = getQuantityByOrderRequest(orderRequest);
        Float totalDelivered = getQuantityDeliveredByOrderRequest(orderRequest);
        Integer difference = total.compareTo(totalDelivered);
        SolicitationRequest solicitationRequest = orderRequestDAO.findById(OrderRequest.class,orderRequest.getId()).getBudgetQuotation().getQuotationRequest().getSolicitationRequest();
        if (difference == 0){
            solicitationRequest.setStatus(StatusEnum.Delivered);
        }
        if (difference == -1) {
            solicitationRequest.setStatus(StatusEnum.PartiallyDelivered);
        }
        solicitationRequestDAO.save(solicitationRequest);
    }
}

