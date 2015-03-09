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
@Table(name = "quotation_request", schema = "", catalog = "purchasing")
public class QuotationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "quotationRequest")
    private List<BudgetQuotation> budgetQuotations;

    @ManyToOne
    @JoinColumn(name = "quotation_id", referencedColumnName = "id", nullable = false)
    private Quotation quotation;

    @OneToOne
    @JoinColumn(name = "solicitation_request_id", referencedColumnName = "id")
    private SolicitationRequest solicitationRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BudgetQuotation> getBudgetQuotations() {
        return budgetQuotations;
    }

    public void setBudgetQuotations(List<BudgetQuotation> budgetQuotations) {
        this.budgetQuotations = budgetQuotations;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public SolicitationRequest getSolicitationRequest() {
        return solicitationRequest;
    }

    public void setSolicitationRequest(SolicitationRequest solicitationRequest) {
        this.solicitationRequest = solicitationRequest;
    }
}
