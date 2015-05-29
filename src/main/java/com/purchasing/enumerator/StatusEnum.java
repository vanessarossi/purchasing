package com.purchasing.enumerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public enum StatusEnum {

    Open("Aberta",false,true,true,false),
    WaitingApproval("Aguardando aprovação",true,false,false,false),
    Approved("Aprovada",true,false,true,false),
    Reject("Recusada",true,false,true,false),
    PreAnalysisReject("Pré analise recusada",true,false,false,false),
    InAnalysis("Em analise",true,false,false,false),
    QuotingProcess("Processo de cotação",true,false,false,false),
    AnalysisQuote("Cotação em analise",true,false,false,false),
    QuoteApproved("Cotação aprovada",true,false,false,false),
    QuoteReject("Cotação recusada",true,false,false,false),
    PartiallyQuoteApproved("Cotação parcialmente aprovada",true,false,false,false),
    BuyingProcess("Em processo de compra",true,false,false,false),
    PurchaseMade("Compra realizada/Aguardando entrega",true,false,true,false),
    Reproved("Reprovado",false,false,false,true),
    Delivered("Entregue",false,false,false,true),
    PartiallyDelivered("Entregue parcialmente",false,false,false,true),
    Conferred("Conferida",true,false,true,false),
    Finished("Finalizada",true,true,true,false),
    PartiallyFinished("Finalizada parcialmente",true,false,true,false),
    CancellationRequest("Pedido de cancelamento",true,false,false,false),
    Canceled("Cancelada",true,true,true,false),
    QuotationCanceled("Cotação Cancelada",true,false,false,false),
    PurchaseOrderCanceled("Ordem de Compra Cancelada",true,false,false,false);

    private String description;
    private Boolean hasOnSolicitation;
    private Boolean hasOnQuotation;
    private Boolean hasOnPurchaseOrder;
    private Boolean hasOnProduct;

    StatusEnum(String description, Boolean hasOnSolicitation, Boolean hasOnQuotation, Boolean hasOnPurchaseOrder,Boolean hasOnProduct) {
        this.description = description;
        this.hasOnSolicitation = hasOnSolicitation;
        this.hasOnQuotation = hasOnQuotation;
        this.hasOnPurchaseOrder = hasOnPurchaseOrder;
        this.hasOnProduct = hasOnProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasOnSolicitation() {
        return hasOnSolicitation;
    }

    public void setHasOnSolicitation(Boolean hasOnSolicitation) {
        this.hasOnSolicitation = hasOnSolicitation;
    }

    public Boolean getHasOnQuotation() {
        return hasOnQuotation;
    }

    public void setHasOnQuotation(Boolean hasOnQuotation) {
        this.hasOnQuotation = hasOnQuotation;
    }

    public Boolean getHasOnPurchaseOrder() {
        return hasOnPurchaseOrder;
    }

    public void setHasOnPurchaseOrder(Boolean hasOnPurchaseOrder) {
        this.hasOnPurchaseOrder = hasOnPurchaseOrder;
    }

    public Boolean getHasOnProduct() {
        return hasOnProduct;
    }

    public void setHasOnProduct(Boolean hasOnProduct) {
        this.hasOnProduct = hasOnProduct;
    }

    /** Status agrupados devido a utilização  **/
    public static List<StatusEnum> getStatusSolicitation(){
        List<StatusEnum> statusEnumList = new ArrayList<>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.hasOnSolicitation){
                statusEnumList.add(statusEnum);
            }
        }
        return statusEnumList;
    }

    public static List<StatusEnum> getStatusQuotation(){
        List<StatusEnum> statusEnumList = new ArrayList<>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.hasOnQuotation){
                statusEnumList.add(statusEnum);
            }
        }
        return statusEnumList;
    }

    public static List<StatusEnum> getStatusPurchaseOrder(){
        List<StatusEnum> statusEnumList = new ArrayList<>();
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.hasOnPurchaseOrder){
                statusEnumList.add(statusEnum);
            }
        }
        return statusEnumList;
    }

    /** Search for class DAO  **/

    public static List<StatusEnum> getStatusForSearchPurchaseOrderBySupplier() {
        List<StatusEnum> statusEnums = new ArrayList<>();
        statusEnums.add(PurchaseMade);
        statusEnums.add(Conferred);
        statusEnums.add(PartiallyFinished);
        return statusEnums;
    }

    public static List<StatusEnum> getStatusSearchPurchaseOrderForConference(){
        List<StatusEnum> statusEnums = new ArrayList<>();
        statusEnums.add(PurchaseMade);
        statusEnums.add(Conferred);
        statusEnums.add(PartiallyFinished);
        return statusEnums;
    }

    public static List<StatusEnum> getStatusSearchJustificationSolicitation(){
        List<StatusEnum> statusEnums = new ArrayList<>();
        statusEnums.add(Reject);
        statusEnums.add(Canceled);
        return statusEnums;
    }
}
