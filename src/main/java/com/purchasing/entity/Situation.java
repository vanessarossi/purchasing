package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "situation", schema = "", catalog = "purchasing")
public class Situation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;

    @Column(name = "date_approval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateApproval;

    @Length(min = 1, max = 1000)
    @Column(name = "justification_cancellation")
    private String justificationCancellation;

    @Length(min = 1, max = 1000)
    @Column(name = "justification_disapproval")
    private String justificationDisapproval;

    @OneToOne
    private Solicitation solicitation;
}
