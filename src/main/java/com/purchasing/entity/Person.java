package com.purchasing.entity;

import com.purchasing.enumerator.TypePersonEnum;
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

/**
 * @author vanessa
 */
@Entity
@Table(name = "person", schema = "", catalog = "purchasing")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private TypePersonEnum typePerson;

    @Transient
    @OneToOne(mappedBy="naturalPerson")
   	private NaturalPerson naturalPerson;

    @Transient
    @OneToOne(mappedBy="juristicPerson")
    private JuristicPerson juristicPerson;
}
