package com.purchasing.entity;

import com.purchasing.enumerator.TypeEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "solicitation", schema = "", catalog = "purchasing")
public class Solicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private TypeEnum type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date initialDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finalDate;
    private Boolean urgency;
    private Boolean emergency;
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

}
