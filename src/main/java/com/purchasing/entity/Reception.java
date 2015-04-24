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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_conferred")
    private Date dateConferred;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @OneToMany(mappedBy = "reception")
    private List<RequestDelivered> requestDelivereds;

}
