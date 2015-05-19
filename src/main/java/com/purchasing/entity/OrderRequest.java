package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "order_request")
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "budget_quotation_id", referencedColumnName = "id")
    private BudgetQuotation budgetQuotation;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @OneToMany(mappedBy = "orderRequest")
    private List<RequestDelivered> requestDelivereds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BudgetQuotation getBudgetQuotation() {
        return budgetQuotation;
    }

    public void setBudgetQuotation(BudgetQuotation budgetQuotation) {
        this.budgetQuotation = budgetQuotation;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public List<RequestDelivered> getRequestDelivereds() {
        return requestDelivereds;
    }

    public void setRequestDelivereds(List<RequestDelivered> requestDelivereds) {
        this.requestDelivereds = requestDelivereds;
    }
}
