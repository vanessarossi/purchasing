package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "company", schema = "", catalog = "purchasing")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Length(min = 1, max = 200)
    @Column(name = "corporate_name")
    private String corporateName;

    @Length(min = 1, max = 200)
    @Column(name = "corporate_name")
    private String companyName;

    @Length(min = 1, max = 20)
    @Column(name = "corporate_name")
    private String cnpj;

    @OneToMany(mappedBy = "company")
    private List<CostCenter> costCenters;

}