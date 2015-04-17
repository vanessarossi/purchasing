package com.purchasing.service.impl;

import com.purchasing.entity.Budget;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Supplier;
import com.purchasing.support.purchaseOrder.OrderRequestProductView;

import java.util.List;

/**
 * @author vanessa
 */
public interface PurchaseOrderService {

    public PurchaseOrder singleSave(Budget budget);
    public List<PurchaseOrder> variousSave(List<PurchaseOrder> purchaseOrders);

    public PurchaseOrder findById(PurchaseOrder purchaseOrder);
    public List<PurchaseOrder> findBySupplierOrderDate(Supplier supplier);

    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public List<Object[]> findPaginationMissing(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationMissing(String sSearch);

    public PurchaseOrder findByConference(Long id);

    public List<OrderRequestProductView> groupByProduct(PurchaseOrder purchaseOrder);

    public void approve(PurchaseOrder purchaseOrder);
    public void reprove(PurchaseOrder purchaseOrder, String justification);

}
