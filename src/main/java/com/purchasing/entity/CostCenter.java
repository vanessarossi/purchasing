package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * @author vanessa
 */
@Entity
@Table(name="cost_center", schema = "", catalog = "purchasing")
public class CostCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(name = "code")
    private String code;

    @NotBlank
    @Length(min = 1, max = 20)
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @ManyToMany
   	@JoinTable(name = "cost_center_user",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "cost_center_id") )
   	private List<User> users;
}
