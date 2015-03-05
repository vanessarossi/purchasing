package com.purchasing.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "service", schema = "", catalog = "purchasing")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_service_id", referencedColumnName = "id", nullable = false)
    private TypeService typeService;

    @OneToMany(mappedBy = "service")
    private List<SolicitationRequest> solicitationRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public List<SolicitationRequest> getSolicitationRequests() {
        return solicitationRequests;
    }

    public void setSolicitationRequests(List<SolicitationRequest> solicitationRequests) {
        this.solicitationRequests = solicitationRequests;
    }
}
