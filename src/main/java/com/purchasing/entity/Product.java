package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @NotBlank
    @Length(min = 1, max = 200)
    @Column(name = "model")
    private String model;
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "mark")
    private String mark;
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "observation")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
    private Unit unit;

    @OneToMany(mappedBy = "product")
    private List<SolicitationRequest> solicitationRequests;
}
