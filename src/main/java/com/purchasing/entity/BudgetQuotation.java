package com.purchasing.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * @author vanessa
 */
@Entity
@Table(name = "budget_quotation")
public class BudgetQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chosen_budget")
    private Boolean chosenBudget;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "unity_price")
    private BigDecimal unityPrice;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "quotation_request_id", referencedColumnName = "id")
    private QuotationRequest quotationRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getChosenBudget() {
        return chosenBudget;
    }

    public void setChosenBudget(Boolean chosenBudget) {
        this.chosenBudget = chosenBudget;
    }

    public BigDecimal getUnityPrice() {
        return unityPrice;
    }

    public void setUnityPrice(BigDecimal unityPrice) {
        this.unityPrice = unityPrice;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public QuotationRequest getQuotationRequest() {
        return quotationRequest;
    }

    public void setQuotationRequest(QuotationRequest quotationRequest) {
        this.quotationRequest = quotationRequest;
    }
}
