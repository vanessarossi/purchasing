package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.service.impl.PurchaseOrderService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public PurchaseOrder singleSave(Budget budget) {
        budget = budgetDAO.findById(Budget.class, budget.getId());

        PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setReception(null);
            purchaseOrder.setDeliveryInformation(null);
            purchaseOrder.setPaymentInformation(null);
            purchaseOrder.setApproval(null);
            purchaseOrder.setBudget(budget);

            purchaseOrder.setDate(new Date());
            purchaseOrder.setStatus(StatusEnum.Open);

        purchaseOrder = purchaseOrderDAO.save(purchaseOrder);

        for (BudgetQuotation budgetQuotation : budget.getBudgetQuotations()) {
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setPurchaseOrder(purchaseOrder);
            orderRequest.setBudgetQuotation(budgetQuotation);

            orderRequestDAO.save(orderRequest);

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
    public List<PurchaseOrder> variousSave(PurchaseOrder purchaseOrder) {
        return null;
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
            String colButtonView = "";

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
        switch (getUserLogged().getId().toString()){
            case "2":
                purchaseOrders = purchaseOrderDAO.paginationMissingDirectorship(search, iDisplayStart, iDisplayLength);
                break;
            case "3":
                purchaseOrders = purchaseOrderDAO.paginationMissingDirector(search, iDisplayStart, iDisplayLength);
                break;
            case "4":
                purchaseOrders = purchaseOrderDAO.paginationMissingManager(search,iDisplayStart,iDisplayLength);
                break;
            case  "5":
                purchaseOrders = purchaseOrderDAO.paginationMissingAnalyst(search,iDisplayStart,iDisplayLength);
                break;
        }
        List<Object[]> purchaseOrderList = new ArrayList<>();

        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            String colCode = purchaseOrder.getId().toString();
            String colSupplier = purchaseOrder.getBudget().getSupplier().getPerson().getName();
            String colButtonEdit = "";
            String colButtonView = "";

            String[] row = {
                    colCode,
                    colSupplier,
                    colButtonEdit,
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
                total = purchaseOrderDAO.totalPaginationMissingDirectorship(search);
                break;
            case "3":
                total = purchaseOrderDAO.totalPaginationMissingDirector(search);
                break;
            case "4":
                total = purchaseOrderDAO.totalPaginationMissingManager(search);
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

    public User getUserLogged() {
        User user = (User) httpSession.getAttribute("userLogged");
        return user;
    }
}
