package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "juristic_person", schema = "", catalog = "purchasing")
@PrimaryKeyJoinColumn(name = "id")
public class JuristicPerson extends Person{

    @NotBlank
    @Length(min = 1, max = 200)
    @Column(name = "company_name")
    private String companyName;


    @Length(min = 1, max = 45)
    @Column(name = "municipal_inscription")
    private String municipalInscription;

    @NotBlank
    @Length(min = 1, max = 45)
    @Column(name = "state_inscription")
    private String stateInscription;

    @NotBlank
    @Length(min = 1, max = 30)
    @Column(name = "cnpj", unique = true)
    private String cnpj;

}
