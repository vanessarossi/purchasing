package com.purchasing.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "initial_date")
    @Temporal(TemporalType.DATE)
    private Date initialDate;

    @NotNull
    @Column(name = "final_date")
    @Temporal(TemporalType.DATE)
    private Date finalDate;

    @Column(name = "observation")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "contract")
    private List<PaymentInformation> paymentInformations;

    @OneToMany(mappedBy = "contract")
    private List<RenewalContract> renewalContracts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<PaymentInformation> getPaymentInformations() {
        return paymentInformations;
    }

    public void setPaymentInformations(List<PaymentInformation> paymentInformations) {
        this.paymentInformations = paymentInformations;
    }

    public List<RenewalContract> getRenewalContracts() {
        return renewalContracts;
    }

    public void setRenewalContracts(List<RenewalContract> renewalContracts) {
        this.renewalContracts = renewalContracts;
    }
}
