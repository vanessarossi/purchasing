package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "state", schema = "", catalog = "purchasing")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Length(min = 1,max = 3)
    @Column(name = "acronym")
    private String acronym;

    @NotNull
    @Length(min = 1,max = 100)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "state")
    private List<Address> addresses;
}
