package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
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
    @Column(name = "parcels")
    private Integer parcels;

    @NotNull
    @Column(name = "interval_day")
    private Integer intervalDay;

    @OneToMany(mappedBy = "formPayment")
    private List<PaymentInformation> paymentInformations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInput() {
        return input;
    }

    public void setInput(Boolean input) {
        this.input = input;
    }

    public Integer getParcels() {
        return parcels;
    }

    public void setParcels(Integer parcels) {
        this.parcels = parcels;
    }

    public Integer getIntervalDay() {
        return intervalDay;
    }

    public void setIntervalDay(Integer intervalDay) {
        this.intervalDay = intervalDay;
    }

    public List<PaymentInformation> getPaymentInformations() {
        return paymentInformations;
    }

    public void setPaymentInformations(List<PaymentInformation> paymentInformations) {
        this.paymentInformations = paymentInformations;
    }
}
