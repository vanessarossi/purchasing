package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * @author vanessa
 */
@Entity
@Table(name = "delivery_information", schema = "", catalog = "purchasing")
public class DeliveryInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   @Length(min = 1, max = 150)
   @Column(name = "street")
    private String street;

    @Length(min = 1, max = 100)
    @Column(name = "neighborhood")
    private String neighborhood;

    @Length(min = 1, max = 45)
    @Column(name = "number")
    private String number;

    @Length(min = 1, max = 150)
    @Column(name = "receptor_name")
    private String receptorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReceptorName() {
        return receptorName;
    }

    public void setReceptorName(String receptorName) {
        this.receptorName = receptorName;
    }
}
