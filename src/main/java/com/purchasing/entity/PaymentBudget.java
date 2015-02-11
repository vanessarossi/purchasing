package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "payment_budget", schema = "", catalog = "purchasing")
public class PaymentBudget {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "payment_information_id", referencedColumnName = "id", nullable = false)
        private PaymentInformation paymentInformation;

        @ManyToOne
        @JoinColumn(name = "budget_quotation_id", referencedColumnName = "id", nullable = false)
        private BudgetQuotation budgetQuotation;

}
