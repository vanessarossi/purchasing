package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "renewal_contract", schema = "", catalog = "purchasing")
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

    @Length(min = 1,max = 100)
    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id", nullable = false)
    private Contract contract;

}
