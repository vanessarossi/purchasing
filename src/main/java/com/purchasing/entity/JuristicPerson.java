package com.purchasing.entity;

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

    @Column(name = "company_name", nullable = false, insertable = true, updatable = true, length = 200)
    private String companyName;

    @Column(name = "municipal_inscription", nullable = true, insertable = true, updatable = true, length = 45)
    private String municipalInscription;

    @Column(name = "state_inscription", nullable = true, insertable = true, updatable = true, length = 45)
    private String stateInscription;

    @Column(name = "cnpj", nullable = false, insertable = true, updatable = true, length = 30 , unique = true)
    private String cnpj;

}
