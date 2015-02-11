package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author vanessa
 */
@Entity
@Table(name = "budget_quotation", schema = "", catalog = "purchasing")
public class BudgetQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean chosenBudget;

    private BigDecimal unityPrice;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", nullable = false)
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "quotation_request_id", referencedColumnName = "id", nullable = false)
    private QuotationRequest quotationRequest;

    @OneToOne
    private OrderRequest orderRequest;
}
