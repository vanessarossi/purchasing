package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "product", schema = "", catalog = "purchasing")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 1, max = 200)
    @Column(name = "description")
    private String description;

    @Length(min = 1, max = 200)
    @Column(name = "model")
    private String model;

    @Length(min = 1, max = 100)
    @Column(name = "mark")
    private String mark;

    @Length(min = 1, max = 100)
    @Column(name = "observation")
    private String observation;

    @Length(min = 1, max = 45)
    @Column(name = "bar_code")
    private String barCode;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "minimum_stock")
    private Float minimumStock;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
    private Unit unit;

    @OneToMany(mappedBy = "product")
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Float getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Float minimumStock) {
        this.minimumStock = minimumStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<SolicitationRequest> getSolicitationRequests() {
        return solicitationRequests;
    }

    public void setSolicitationRequests(List<SolicitationRequest> solicitationRequests) {
        this.solicitationRequests = solicitationRequests;
    }
}
