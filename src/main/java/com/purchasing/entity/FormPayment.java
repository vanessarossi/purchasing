package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "form_payment", schema = "", catalog = "purchasing")
public class FormPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Boolean input;

    private Integer parcels;

    private Integer intervalDay;

    @OneToMany(mappedBy = "formPayment")
    private List<PaymentInformation> paymentInformations;
}
