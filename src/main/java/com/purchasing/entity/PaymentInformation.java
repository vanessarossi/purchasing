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
}
