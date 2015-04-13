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

    public List<Object[]> paginationMissingAnalyst(String sSearch, int iDisplayStart, int iDisplayLength);

    public Integer totalPaginationMissingAnalyst(String sSearch);

    public List<Object[]> paginationMissingManager(String sSearch, int iDisplayStart, int iDisplayLength);

    public Integer totalPaginationMissingManager(String sSearch);

    public List<Object[]> paginationMissingDirector(String sSearch, int iDisplayStart, int iDisplayLength);

    public Integer totalPaginationMissingDirector(String sSearch);

    public List<Object[]> paginationMissingDirectorship(String sSearch, int iDisplayStart, int iDisplayLength);

    public Integer totalPaginationMissingDirectorship(String sSearch);

    public PurchaseOrder findByConference(Long id);

}
