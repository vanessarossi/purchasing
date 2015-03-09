package com.purchasing.entity;

import com.purchasing.enumerator.MeanPaymentEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "payment_information", schema = "", catalog = "purchasing")
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "contratct")
    private Boolean contratct;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @Column(name = "mean_payment")
    private MeanPaymentEnum meanPayment;

    @Column(name = "date_first_installment")
    @Temporal(TemporalType.DATE)
    private Date dateFirstInstallment;

    @Column(name = "date_last_installment")
    @Temporal(TemporalType.DATE)
    private Date dateLastInstallment;

    @Column(name = "date_input")
    @Temporal(TemporalType.DATE)
    private Date dateInput;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "input_price")
    private BigDecimal inputPrice;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "share_price")
    private BigDecimal sharePrice;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_final_price")
    private BigDecimal totalFinalPrice;

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id" , nullable = true)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "form_payment_id", referencedColumnName = "id" ,nullable = false)
    private FormPayment formPayment;

    @OneToOne
    private PurchaseOrder purchaseOrder;

    @OneToOne
    private AdditionalPurchaseOrder additionalPurchaseOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getContratct() {
        return contratct;
    }

    public void setContratct(Boolean contratct) {
        this.contratct = contratct;
    }

    public MeanPaymentEnum getMeanPayment() {
        return meanPayment;
    }

    public void setMeanPayment(MeanPaymentEnum meanPayment) {
        this.meanPayment = meanPayment;
    }

    public Date getDateFirstInstallment() {
        return dateFirstInstallment;
    }

    public void setDateFirstInstallment(Date dateFirstInstallment) {
        this.dateFirstInstallment = dateFirstInstallment;
    }

    public Date getDateLastInstallment() {
        return dateLastInstallment;
    }

    public void setDateLastInstallment(Date dateLastInstallment) {
        this.dateLastInstallment = dateLastInstallment;
    }

    public Date getDateInput() {
        return dateInput;
    }

    public void setDateInput(Date dateInput) {
        this.dateInput = dateInput;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(BigDecimal inputPrice) {
        this.inputPrice = inputPrice;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getTotalFinalPrice() {
        return totalFinalPrice;
    }

    public void setTotalFinalPrice(BigDecimal totalFinalPrice) {
        this.totalFinalPrice = totalFinalPrice;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public FormPayment getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(FormPayment formPayment) {
        this.formPayment = formPayment;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public AdditionalPurchaseOrder getAdditionalPurchaseOrder() {
        return additionalPurchaseOrder;
    }

    public void setAdditionalPurchaseOrder(AdditionalPurchaseOrder additionalPurchaseOrder) {
        this.additionalPurchaseOrder = additionalPurchaseOrder;
    }
}
