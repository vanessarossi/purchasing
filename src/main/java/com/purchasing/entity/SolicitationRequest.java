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
@Table(name = "solicitation_request")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Boolean getAddQuotation() {
        return addQuotation;
    }

    public void setAddQuotation(Boolean addQuotation) {
        this.addQuotation = addQuotation;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Solicitation getSolicitation() {
        return solicitation;
    }

    public void setSolicitation(Solicitation solicitation) {
        this.solicitation = solicitation;
    }
}
