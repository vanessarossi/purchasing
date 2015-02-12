package com.purchasing.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vanessa
 */
@Entity
@Table(name = "contact", schema = "", catalog = "purchasing")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 1, max = 15)
    @Column(name = "phone")
    private String phone;

    @Length(min = 1, max = 15)
    @Column(name = "secondary_phone")
    private String secondaryPhone;

    @Length(min = 1, max = 15)
    @Column(name = "cell_phone")
    private String cellPhone;

    @Length(min = 1, max = 15)
    @Column(name = "secondary_cell_phone")
    private String secondaryCellPhone;

    @Email
    @Length(min = 1, max = 200)
    @Column(name = "email")
    private String email;

    @Length(min = 1, max = 200)
    @Column(name = "website")
    private String website;

    @Length(min = 1, max = 100)
    @Column(name = "contact_name")
    private String contactName;
}
