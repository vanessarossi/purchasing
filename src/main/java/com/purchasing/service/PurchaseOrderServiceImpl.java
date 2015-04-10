package com.purchasing.service;

import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Supplier;
import com.purchasing.service.impl.PurchaseOrderService;

import java.util.List;

/**
 * @author vanessa
 */
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Override
    public PurchaseOrder singleSave(PurchaseOrder purchaseOrder) {
        return null;
    }

    @Override
    public List<PurchaseOrder> variousSave(PurchaseOrder purchaseOrder) {
        return null;
    }

    @Override
    public List<PurchaseOrder> findBySupplierOrderDate(Supplier supplier) {
        return null;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        return null;
    }

    @Override
    public List<Object[]> findMissingPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalMissingPagination(String sSearch) {
        return null;
    }
}
