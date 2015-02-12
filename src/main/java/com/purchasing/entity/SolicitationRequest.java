package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "solicitation_request", schema = "", catalog = "purchasing")
public class SolicitationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Column(name = "add_quotation")
    private Boolean addQuotation;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "solicitation_id", referencedColumnName = "id", nullable = false)
    private Solicitation solicitation;
}
