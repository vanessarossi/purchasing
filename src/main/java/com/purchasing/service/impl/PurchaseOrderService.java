package com.purchasing.service.impl;

import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.support.purchaseOrder.OrderRequestProductView;
import com.purchasing.support.purchaseOrder.printer.PurchaseOrderViewPrinter;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author vanessa
 */
public interface PurchaseOrderService {

    public PurchaseOrder singleSave(Budget budget);
    public List<PurchaseOrder> variousSave(List<PurchaseOrder> purchaseOrders);
    public PurchaseOrder singleSaveWithJustification(Budget budget, String justification, Boolean exclusive);
    public void saveDeliveryAndPayment(PurchaseOrder purchaseOrder);

    public void saveCancelation(PurchaseOrder purchaseOrder);

    public PurchaseOrder findById(PurchaseOrder purchaseOrder);
    public List<PurchaseOrder> findBySupplierOrderDate(Supplier supplier);

    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public List<Object[]> findPaginationWithFilter(String sSearch,StatusEnum status,int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationWithFilter(String sSearch,StatusEnum status);

    public List<Object[]> findPaginationMissing(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationMissing(String sSearch);

    public List<Object[]> findPaginationMissingConfered(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationMissingConfered(String sSearch);

    public PurchaseOrder findByConference(Long id);

    public List<OrderRequestProductView> groupByProduct(PurchaseOrder purchaseOrder);

    public List<PurchaseOrderViewPrinter> groupByCostCenter(List<PurchaseOrderViewPrinter> purchaseOrderViewPrinters);

    public void approve(PurchaseOrder purchaseOrder, String observation);
    public void reprove(PurchaseOrder purchaseOrder, String justification);

    public File printerOrder(PurchaseOrder purchaseOrder);
    public File printerPurchaseOrder(Reception reception);

    public Float getQuantityByOrderRequest(OrderRequest orderRequest);
    public Float getQuantityDeliveredByOrderRequest(OrderRequest orderRequest);

    public  BigDecimal getPriceTotalByOrderRequest(OrderRequest orderRequest);
    public  BigDecimal getPriceDeliveredByOrderRequest(OrderRequest orderRequest);

    public void saveReception(Reception reception,StatusEnum statusEnum);

    public void saveConference(Reception reception);

    public Reception findReceptionById(Reception reception);

    public BigDecimal sumTotal(List<RequestDelivered>requestDelivereds,PurchaseOrder purchaseOrder);
}
