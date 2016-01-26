package com.purchasing.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Length(min = 1, max = 200)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "username")
    private String username;


    @Length(min = 1, max = 40)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "email")
    @Email
    private String email;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_access")
    private Date lastAccess;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "cost_center_user",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "cost_center_id"))
    private List<CostCenter> costCenters;

    @OneToMany(mappedBy = "user")
    private List<Reception> receptions;

    @OneToMany(mappedBy = "user")
    private List<Quotation> quotations;

    @OneToMany(mappedBy = "user")
    private List<Solicitation> solicitations;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<CostCenter> getCostCenters() {
        return costCenters;
    }

    public void setCostCenters(List<CostCenter> costCenters) {
        this.costCenters = costCenters;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public List<Solicitation> getSolicitations() {
        return solicitations;
    }

    public void setSolicitations(List<Solicitation> solicitations) {
        this.solicitations = solicitations;
    }

    public boolean containsCostCenter(CostCenter costCenter){
            for (CostCenter costC : this.getCostCenters()){
                if (costCenter.getId() == costC.getId()){
                    return true;
                }
            }
        return false ;
    }
}
