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
@Table(name = "natural_person")
@PrimaryKeyJoinColumn(name = "id")
public class NaturalPerson extends Person {

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(name = "cpf", unique = true)
    private String cpf;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(name = "rg", unique = true)
    private String rg;

    @NotBlank
    @Length(min = 1, max = 6)
    @Column(name = "emitting_organ", unique = true)
    private String emittingOrgan;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmittingOrgan() {
        return emittingOrgan;
    }

    public void setEmittingOrgan(String emittingOrgan) {
        this.emittingOrgan = emittingOrgan;
    }

    public void setPerson(Person person) {
        super.setName(person.getName());
        super.setTypePerson(person.getTypePerson());
    }
}
