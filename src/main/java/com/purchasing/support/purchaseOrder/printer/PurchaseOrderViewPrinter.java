package com.purchasing.support.purchaseOrder.printer;

import com.purchasing.entity.RequestDelivered;
import com.purchasing.enumerator.TypeEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Vanessa on 4/28/15.
 */
public class PurchaseOrderViewPrinter implements Comparator<PurchaseOrderViewPrinter> {

    private String code_cost_center;
    private String description_cost_center;
    private String total_price;

    public String getCode_cost_center() {
        return code_cost_center;
    }

    public void setCode_cost_center(String code_cost_center) {
        this.code_cost_center = code_cost_center;
    }

    public String getDescription_cost_center() {
        return description_cost_center;
    }

    public void setDescription_cost_center(String description_cost_center) {
        this.description_cost_center = description_cost_center;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<PurchaseOrderViewPrinter> generateList(List<RequestDelivered> requestDelivereds) {
        List<PurchaseOrderViewPrinter> purchaseOrderViewPrinters = new ArrayList<>();
        for (RequestDelivered requestDelivered : requestDelivereds){


            PurchaseOrderViewPrinter purchaseOrderViewPrinter  = new PurchaseOrderViewPrinter();
            purchaseOrderViewPrinter.setCode_cost_center(requestDelivered.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation().getCostCenter().getCode());
            purchaseOrderViewPrinter.setDescription_cost_center(requestDelivered.getOrderRequest().getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getSolicitation().getCostCenter().getDescription());

            if (requestDelivered.getReception().getPurchaseOrder().getBudget().getQuotation().getType().equals(TypeEnum.Material)){
                BigDecimal quantity = requestDelivered.getQuantity() == null ? new BigDecimal(1f) : new BigDecimal(requestDelivered.getQuantity());
                BigDecimal resultMaterial = requestDelivered.getOrderRequest().getBudgetQuotation().getUnityPrice().multiply(quantity);
                purchaseOrderViewPrinter.setTotal_price(resultMaterial.toString().replace(".",","));

            }if (requestDelivered.getReception().getPurchaseOrder().getBudget().getQuotation().getType().equals(TypeEnum.Service)){
                BigDecimal resultService = requestDelivered.getPrice();
                purchaseOrderViewPrinter.setTotal_price(resultService.toString().replace(".",","));
            }
            purchaseOrderViewPrinters.add(purchaseOrderViewPrinter);
        }
        return purchaseOrderViewPrinters;
    }

    @Override
    public int compare(PurchaseOrderViewPrinter o1, PurchaseOrderViewPrinter o2) {
        return 0;
    }
}
