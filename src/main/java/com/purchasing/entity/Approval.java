package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
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

    @Length(min = 1, max = 20)
    @Column(name = "user_first_approval")
    private Long userFirstApproval;

    private Boolean secondApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSecondApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_second_approval")
    private Long userSecondApproval;

    private Boolean thirdApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateThirdApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_third_approval")
    private Long userThirdApproval;

    private Boolean fourthApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fourthApprovalDate;

    @Length(min = 1, max = 20)
    @Column(name = "user_fourth_approval")
    private Long userFourthApproval;

    private String justificationDisapproval;

    @OneToOne
    private PurchaseOrder purchaseOrder;
}
