package com.purchasing.printer.base;

import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Reception;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.service.impl.QuotationService;

import java.io.File;

/**
 * @author vanessa
 */
public interface Printer {

    public File generate(Long code);
    public File generateOrder(Long code, PurchaseOrderService purchaseOrderService);
    public File generateOrderBudget(Long code, QuotationService quotationService);
    public File generatePurchaseOrder(PurchaseOrder purchaseOrder, Reception reception, PurchaseOrderService purchaseOrderService);
}
