package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "quotation", schema = "", catalog = "purchasing")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private TypeEnum type;

    @NotNull
    @Column(name = "initial_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initialDate;

    @Column(name = "final_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalDate;

    @Column(name = "exclusive_supplier")
    private Boolean exclusiveSupplier;

    @Column(name = "justification")
    private String justification;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "quotation")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Budget> budgets;

    @OneToMany(mappedBy = "quotation")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<QuotationRequest> quotationRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Boolean getExclusiveSupplier() {
        return exclusiveSupplier;
    }

    public void setExclusiveSupplier(Boolean exclusiveSupplier) {
        this.exclusiveSupplier = exclusiveSupplier;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public List<QuotationRequest> getQuotationRequests() {
        return quotationRequests;
    }

    public void setQuotationRequests(List<QuotationRequest> quotationRequests) {
        this.quotationRequests = quotationRequests;
    }
}
