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
@Table(name = "juristic_person")
@PrimaryKeyJoinColumn(name = "id")
public class JuristicPerson extends Person{

    @NotBlank
    @Length(min = 1, max = 200)
    @Column(name = "company_name")
    private String companyName;

    @Length(min = 1, max = 45)
    @Column(name = "municipal_inscription")
    private String municipalInscription;
    
    @Length(min = 1, max = 45)
    @Column(name = "state_inscription")
    private String stateInscription;

    @NotBlank
    @Length(min = 1, max = 30)
    @Column(name = "cnpj", unique = true)
    private String cnpj;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMunicipalInscription() {
        return municipalInscription;
    }

    public void setMunicipalInscription(String municipalInscription) {
        this.municipalInscription = municipalInscription;
    }

    public String getStateInscription() {
        return stateInscription;
    }

    public void setStateInscription(String stateInscription) {
        this.stateInscription = stateInscription;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setPerson(Person person) {
        super.setName(person.getName());
        super.setTypePerson(person.getTypePerson());
    }
}
