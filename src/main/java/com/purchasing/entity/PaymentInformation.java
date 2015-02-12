package com.purchasing.entity;

import com.purchasing.enumerator.MeanPaymentEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "payment_information", schema = "", catalog = "purchasing")
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean contratct;

    @Enumerated(EnumType.ORDINAL)
    private MeanPaymentEnum meanPayment;

    @Temporal(TemporalType.DATE)
    private Date dateFirstInstallment;

    @Temporal(TemporalType.DATE)
    private Date dateLastInstallment;

    private Date expirationDate;

    private BigDecimal inputPrice;

    private BigDecimal sharePrice;

    private BigDecimal totalPrice;

    private Integer discountPercentage;

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

    @OneToMany(mappedBy = "budget")
    private List<BudgetQuotation> budgetQuotationList;

}
