package com.purchasing.entity;

import com.purchasing.enumerator.TypeEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "solicitation")
public class Solicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private TypeEnum type;

    @Column(name = "initial_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initialDate;

    @Column(name = "final_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalDate;

    @Column(name = "urgency")
    private Boolean urgency;

    @Column(name = "emergency")
    private Boolean emergency;

    @Length(min = 1, max = 1000)
    @Column(name = "observation")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "cost_center_id", referencedColumnName = "id", nullable = false)
    private CostCenter costCenter;

    @OneToOne
    @JoinColumn(name = "situation_id", referencedColumnName = "id")
    private Situation situation;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "solicitation")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SolicitationRequest> solicitationRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Boolean getUrgency() {
        return urgency;
    }

    public void setUrgency(Boolean urgency) {
        this.urgency = urgency;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SolicitationRequest> getSolicitationRequests() {
        return solicitationRequests;
    }

    public void setSolicitationRequests(List<SolicitationRequest> solicitationRequests) {
        this.solicitationRequests = solicitationRequests;
    }
}
