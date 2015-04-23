package com.purchasing.printer.base;

import com.purchasing.service.impl.PurchaseOrderService;

import java.io.File;

/**
 * @author vanessa
 */
public interface Printer {

    public File generate(Long code);
    public File generateOrder(Long code, PurchaseOrderService purchaseOrderService);

}
