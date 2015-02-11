package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

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

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;
    private Boolean addQuotation;
    private Float quantity;

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
