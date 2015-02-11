package com.purchasing.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name = "state", schema = "", catalog = "purchasing")
public class State {

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic
    @Column(name = "acronym", nullable = false, insertable = true, updatable = true, length = 3)
    private String acronym;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100)
    private String name;

    @OneToMany(mappedBy = "state")
    private List<Address> addresses;
}
