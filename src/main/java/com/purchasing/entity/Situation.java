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

    @OneToOne(mappedBy ="situation")
    private Solicitation solicitation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getDateApproval() {
        return dateApproval;
    }

    public void setDateApproval(Date dateApproval) {
        this.dateApproval = dateApproval;
    }

    public String getJustificationCancellation() {
        return justificationCancellation;
    }

    public void setJustificationCancellation(String justificationCancellation) {
        this.justificationCancellation = justificationCancellation;
    }

    public String getJustificationDisapproval() {
        return justificationDisapproval;
    }

    public void setJustificationDisapproval(String justificationDisapproval) {
        this.justificationDisapproval = justificationDisapproval;
    }

    public Solicitation getSolicitation() {
        return solicitation;
    }

    public void setSolicitation(Solicitation solicitation) {
        this.solicitation = solicitation;
    }
}
