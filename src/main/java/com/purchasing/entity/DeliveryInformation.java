package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * @author vanessa
 */
@Entity
@Table(name = "delivery_information")
public class DeliveryInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 1, max = 100)
    @Column(name = "place")
    private String place;

   @Length(min = 1, max = 150)
   @Column(name = "street")
    private String street;

    @Length(min = 1, max = 100)
    @Column(name = "neighborhood")
    private String neighborhood;

    @Length(min = 1, max = 45)
    @Column(name = "number")
    private String number;

    @Column(name = "delivery_time")
    private Integer deliveryTime;

    @Length(min = 1, max = 150)
    @Column(name = "receptor_name")
    private String receptorName;

    @Length(min = 1, max = 45)
    @Column(name = "zip_code")
    private String zipCode;

    @Length(min = 1, max = 100)
    @Column(name = "city")
    private String city;

    @Length(min = 1, max = 20)
    @Column(name = "cnpj")
    private String cnpj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getReceptorName() {
        return receptorName;
    }

    public void setReceptorName(String receptorName) {
        this.receptorName = receptorName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
