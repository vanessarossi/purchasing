package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Length(min = 1, max = 1000)
    @Column(name = "observation")
    private String observation;

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
    private List<Budget> budgets;

    @OneToMany(mappedBy = "quotation")
    private List<QuotationRequest> quotationRequests;
}
