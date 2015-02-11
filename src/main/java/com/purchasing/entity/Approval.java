package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "approval", schema = "", catalog = "purchasing")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean firstApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFirstApproval;

    private Boolean secondApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSecondApproval;

    private Boolean thirdApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateThirdApproval;

    private Boolean fourthApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fourthApprovalDate;

    private String justificationDisapproval;

    @OneToOne
    private PurchaseOrder purchaseOrder;
}
