package com.purchasing.entity;

import com.purchasing.enumerator.StatusEnum;

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
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateApproval;
    private String justificationCancellation;
    private String justificationDisapproval;

    @OneToOne
    private Solicitation solicitation;
}
