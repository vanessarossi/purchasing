package com.purchasing.enumerator;

/**
 * @author vanessa
 */
public enum StatusEnum {

        Open("Aberta",true,true,true,false),
        WaitingApproval("Aguardando aprovação",true,true,false,false),
        Approved("Aprovada",true,true,false,false),
        Reject("Recusada",true,true,false,false),
        PreAnalysisReject("Pré analise recusada",true,false,false,false),
        InAnalysis("Em analise",true,false,false,false),
        QuotingProcess("Processo de cotação",true,false,true,false),
        AnalysisQuote("Cotação em analise",true,false,true,false),
        QuoteApproved("Cotação aprovada",true,false,true,false),
        QuoteReject("Cotação recusada",true,false,true,false),
        PartiallyQuoteApproved("Cotação parcialmente aprovada",true,false,true,false),
        PartiallyQuoteReject("Cotação parcialmente recusada",true,false,true,false),
        BuyingProcess("Em processo de compra",true,true,false,false),
        PurchaseMade("Compra realizada",true,true,false,true),
        PartiallyPurchaseMade("Compra realizada parcialmente",true,true,false,true),
        Delivered("Entregue",false,false,false,true),
        PartiallyDelivered("Entregue parcialmente",true,false,false,true),
        Finished("Finalizada",true,false,false,true),
        PartiallyCompleted("Finalizado parcialmente",true,false,false,false),
        CancellationRequest("Pedido de cancelamento",true,false,false,false),
        Canceled("Cancelada",true,false,false,false);

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
}
