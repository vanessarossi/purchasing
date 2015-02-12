package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "input")
    private Boolean input;

    @NotNull
    @Length(min = 1)
    @Column(name = "parcels")
    private Integer parcels;

    @NotNull
    @Length(min = 1)
    @Column(name = "parcels")
    private Integer intervalDay;

    @OneToMany(mappedBy = "formPayment")
    private List<PaymentInformation> paymentInformations;
}
