package com.purchasing.printer.impl;

import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Reception;
import com.purchasing.printer.base.Printer;
import com.purchasing.service.impl.PurchaseOrderService;

import java.io.File;

/**
 * @author vanessa
 */
public class PrinterImpl extends BasePrinterImpl implements Printer {


    @Override
    public File generate(Long code) {
        return null;
    }

    @Override
    public File generateOrder(Long code, PurchaseOrderService purchaseOrderService) {
        return null;
    }

    @Override
    public File generatePurchaseOrder(PurchaseOrder purchaseOrder, Reception reception, PurchaseOrderService purchaseOrderService) {
        return null;
    }
}
