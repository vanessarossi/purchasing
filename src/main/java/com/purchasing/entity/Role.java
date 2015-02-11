package com.purchasing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author vanessa
 */

@Entity
@Table(name="role", schema = "", catalog = "purchasing")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   	private Long id;
    private String description;
    private BigDecimal minimumValue;
    private BigDecimal  maximumValue;

    @OneToMany(mappedBy = "role")
    private List<User> users;


}
