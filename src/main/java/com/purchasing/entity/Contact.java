package com.purchasing.entity;

import org.hibernate.validator.constraints.Email;

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

    private String phone;

    private String secondaryPhone;

    private String cellPhone;

    private String secondaryCellPhone;

    @Email
    private String email;

    private String website;

    private String contactName;
}
