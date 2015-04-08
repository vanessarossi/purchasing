package com.purchasing.service.impl;

import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Supplier;

import java.util.List;

/**
 * @author vanessa
 */
public interface PurchaseOrderService {

    public PurchaseOrder singleSave(PurchaseOrder purchaseOrder);
    public List<PurchaseOrder> variousSave(PurchaseOrder purchaseOrder);

    public List<PurchaseOrder> findBySupplierOrderDate(Supplier supplier);

    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

}
