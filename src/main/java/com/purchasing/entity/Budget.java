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
    @Column(name = "date")
    private Date date;

    @Column(name = "chosen_budget")
    private Boolean chosenBudget;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(name = "number_budget")
    private String numberBudget;

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

    @OneToMany(mappedBy = "budget")
    private List<PaymentInformationBudget> paymentInformationBudgets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getChosenBudget() {
        return chosenBudget;
    }

    public void setChosenBudget(Boolean chosenBudget) {
        this.chosenBudget = chosenBudget;
    }

    public String getNumberBudget() {
        return numberBudget;
    }

    public void setNumberBudget(String numberBudget) {
        this.numberBudget = numberBudget;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<BudgetQuotation> getBudgetQuotations() {
        return budgetQuotations;
    }

    public void setBudgetQuotations(List<BudgetQuotation> budgetQuotations) {
        this.budgetQuotations = budgetQuotations;
    }

    public List<PaymentInformationBudget> getPaymentInformationBudgets() {
        return paymentInformationBudgets;
    }

    public void setPaymentInformationBudgets(List<PaymentInformationBudget> paymentInformationBudgets) {
        this.paymentInformationBudgets = paymentInformationBudgets;
    }
}
