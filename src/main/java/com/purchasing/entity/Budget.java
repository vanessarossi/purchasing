package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "budget", schema = "", catalog = "purchasing")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Boolean chosenBudget;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(name = "number_budget")
    private String numberBudget;

    @Length(min = 1, max = 2)
    @Column(name = "delivery_time")
    private Integer deliveryTime;

    @ManyToOne
    @JoinColumn(name = "quotation_id", referencedColumnName = "id", nullable = false)
    private Quotation quotation;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "budget")
    private List<BudgetQuotation> budgetQuotations;
}
