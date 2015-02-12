package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "address", schema = "", catalog = "purchasing")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 5, max = 150)
    @Column(name = "street")
    private String street;

    @Length(min = 2, max = 100)
    @Column(name = "neighborhood")
    private String neighborhood;

    @Length(min = 1, max = 10)
    @Column(name = "number")
    private String number;

    @Length(min = 15, max = 15)
    @Column(name = "zip_code")
    private String zipCode;

    @NotBlank
    @Length(min =3, max = 200)
    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false)
    private State state;

}
