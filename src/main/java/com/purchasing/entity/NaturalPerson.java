package com.purchasing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "natural_person", schema = "", catalog = "purchasing")
@PrimaryKeyJoinColumn(name = "id")
public class NaturalPerson extends Person {

    @Column(name = "cpf", nullable = false, insertable = true, updatable = true, length = 20 , unique = true)
    private String cpf;

    @Column(name = "rg", nullable = false, insertable = true, updatable = true, length = 20)
    private String rg;

    @Column(name = "emitting_organ", nullable = false, insertable = true, updatable = true, length = 6)
    private String emittingOrgan;
}
