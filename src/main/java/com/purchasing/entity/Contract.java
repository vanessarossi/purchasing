package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

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
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "contract", schema = "", catalog = "purchasing")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "initital_date")
    @Temporal(TemporalType.DATE)
    private Date inititalDate;

    @NotNull
    @Column(name = "final_date")
    @Temporal(TemporalType.DATE)
    private Date finalDate;

    @Length(min = 1, max = 1000)
    @Column(name = "observation")
    private String observation;

    @Length(min = 1, max = 1000)
    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "contract")
    private List<PaymentInformation> paymentInformations;

    @OneToMany(mappedBy = "contract")
    private List<RenewalContract> renewalContracts;
}
