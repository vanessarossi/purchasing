package com.purchasing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "stock", schema = "", catalog = "purchasing")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "date_last_entry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastEntry;

    @Column(name = "last_departure_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDepartureDate;

    @OneToOne
    private Product product;
}
