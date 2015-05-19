package com.purchasing.entity;

import com.purchasing.enumerator.TypePersonEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * @author vanessa
 */
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 1, max = 200)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "type_person")
    @Enumerated(EnumType.ORDINAL)
    private TypePersonEnum typePerson;

    @Transient
    @OneToOne(mappedBy="naturalPerson")
   	private NaturalPerson naturalPerson;

    @Transient
    @OneToOne(mappedBy="juristicPerson")
    private JuristicPerson juristicPerson;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypePersonEnum getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(TypePersonEnum typePerson) {
        this.typePerson = typePerson;
    }

    public NaturalPerson getNaturalPerson() {
        return naturalPerson;
    }

    public void setNaturalPerson(NaturalPerson naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    public JuristicPerson getJuristicPerson() {
        return juristicPerson;
    }

    public void setJuristicPerson(JuristicPerson juristicPerson) {
        this.juristicPerson = juristicPerson;
    }

    public String getCnpj(){
        return this.getJuristicPerson().getCnpj();
    }

    public String getCpf(){
        return this.getNaturalPerson().getCpf();
    }
}
