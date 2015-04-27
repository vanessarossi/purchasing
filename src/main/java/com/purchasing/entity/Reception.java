package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "reception", schema = "", catalog = "purchasing")
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @NotBlank
    @Length(min = 1, max = 45)
    @Column(name = "tax_document")
    private String taxDocument;

    @Column(name = "bar_code_tax_document")
    private Integer barCodeTaxDocument;


    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @OneToOne
    @JoinColumn(name = "payment_information_id", referencedColumnName = "id" , nullable = false)
    private PaymentInformation paymentInformation;

    @OneToMany(mappedBy = "reception")
    private List<RequestDelivered> requestDelivereds;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTaxDocument() {
        return taxDocument;
    }

    public void setTaxDocument(String taxDocument) {
        this.taxDocument = taxDocument;
    }

    public Integer getBarCodeTaxDocument() {
        return barCodeTaxDocument;
    }

    public void setBarCodeTaxDocument(Integer barCodeTaxDocument) {
        this.barCodeTaxDocument = barCodeTaxDocument;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public List<RequestDelivered> getRequestDelivereds() {
        return requestDelivereds;
    }

    public void setRequestDelivereds(List<RequestDelivered> requestDelivereds) {
        this.requestDelivereds = requestDelivereds;
    }
}
