package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.MeanPaymentEnum;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.support.purchaseOrder.OrderRequestProductView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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

    @Override
    public PurchaseOrder singleSave(Budget budget) {
        budget = budgetDAO.findById(Budget.class, budget.getId());

        PaymentInformation paymentInformation = new PaymentInformation();
          paymentInformation.setMeanPayment(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getMeanPayment());
          paymentInformation.setFormPayment(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getFormPayment());
          paymentInformation.setTotalPrice(budget.getPaymentInformationBudgets().get(0).getPaymentInformation().getTotalPrice());

        paymentInformation = paymentInformationDAO.save(paymentInformation);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setReception(null);
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
            String colButtonEdit = "";
            String colButtonView = "<a href=/purchasing/ordemCompra/visualizar/" + purchaseOrder.getId() +"><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";

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
    public List<Object[]> findPaginationMissing(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        switch (getUserLogged().getRole().getId().toString()){
            case "2":
                purchaseOrders = purchaseOrderDAO.paginationMissingDirectorship(search, getUserLogged().getRole().getMinimumValue(), iDisplayStart, iDisplayLength);
                break;
            case "3":
                purchaseOrders = purchaseOrderDAO.paginationMissingDirector(search, getUserLogged().getRole().getMaximumValue(), iDisplayStart, iDisplayLength);
                break;
            case "4":
                purchaseOrders = purchaseOrderDAO.paginationMissingManager(search, getUserLogged().getRole().getMaximumValue(), iDisplayStart, iDisplayLength);
                break;
            case  "5":
                purchaseOrders = purchaseOrderDAO.paginationMissingAnalyst(search, iDisplayStart, iDisplayLength);
                break;
        }
        List<Object[]> purchaseOrderList = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            String colCode = purchaseOrder.getId().toString();
            String colSupplier = purchaseOrder.getBudget().getSupplier().getPerson().getName();
            String colButtonView = "";

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
        switch (getUserLogged().getId().toString()) {
            case "2":
                total = purchaseOrderDAO.totalPaginationMissingDirectorship(search, getUserLogged().getRole().getMinimumValue());
                break;
            case "3":
                total = purchaseOrderDAO.totalPaginationMissingDirector(search, getUserLogged().getRole().getMinimumValue());
                break;
            case "4":
                total = purchaseOrderDAO.totalPaginationMissingManager(search, getUserLogged().getRole().getMinimumValue());
                break;
            case "5":
                total = purchaseOrderDAO.totalPaginationMissingAnalyst(search);
                break;
        }

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

            purchaseOrder.setReception(null);
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

            purchaseOrder.setReception(null);
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

}
