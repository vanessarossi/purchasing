package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "description")
    private String description;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "minimum_value")
    private BigDecimal minimumValue;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "maximum_value")
    private BigDecimal  maximumValue;

    @OneToMany(mappedBy = "role")
    private List<User> users;


}
