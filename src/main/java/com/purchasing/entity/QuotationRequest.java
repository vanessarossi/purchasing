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
}
