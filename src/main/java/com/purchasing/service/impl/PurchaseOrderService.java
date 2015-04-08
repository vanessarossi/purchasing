package com.purchasing.service.impl;

import com.purchasing.entity.PurchaseOrder;

import java.util.List;

/**
 * @author vanessa
 */
public interface PurchaseOrderService {

    public PurchaseOrder singleSave(PurchaseOrder purchaseOrder);
    public List<PurchaseOrder> variousSave(PurchaseOrder purchaseOrder);

}
