package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;

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

    @Enumerated(EnumType.ORDINAL)
    private TypeEnum type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date initialDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finalDate;

    private String observation;

    private Boolean exclusiveSupplier;

    private String justification;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @OneToMany(mappedBy = "quotation")
    private List<Budget> budgets;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "quotation")
    private List<QuotationRequest> quotationRequests;
}
