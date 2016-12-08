package com.purchasing.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "renewal_contract")
public class RenewalContract {

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

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false)
    private Contract contract;

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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
