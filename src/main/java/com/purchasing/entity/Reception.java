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

    @Column(name = "conferred_import")
    private Boolean conferredImport;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_conferred")
    private Date dateConferred;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Length(min = 1, max = 100)
    @Column(name = "user_conferred")
    private String userConferred;

    @OneToOne(mappedBy = "reception")
    private AdditionalPurchaseOrder additionalPurchaseOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "reception")
    private List<RequestDelivered> requestDelivereds;

}
