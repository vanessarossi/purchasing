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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "bar_code_tax_document")
    private Integer barCodeTaxDocument;

    @OneToOne(mappedBy = "reception")
    private AdditionalPurchaseOrder additionalPurchaseOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "reception")
    private List<RequestDelivered> requestDelivereds;

    @OneToOne
    private PurchaseOrder purchaseOrder;
}
