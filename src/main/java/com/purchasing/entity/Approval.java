package com.purchasing.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * @author vanessa
 */
@Entity
@Table(name = "approval", schema = "", catalog = "purchasing")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean firstApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFirstApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_first_approval")
    private Long userFirstApproval;

    private Boolean secondApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSecondApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_second_approval")
    private Long userSecondApproval;

    private Boolean thirdApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateThirdApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_third_approval")
    private Long userThirdApproval;

    private Boolean fourthApproval;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fourthApprovalDate;

    @Length(min = 1, max = 20)
    @Column(name = "user_fourth_approval")
    private Long userFourthApproval;

    private String justificationDisapproval;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isSecondApproval() {
        return secondApproval;
    }

    public void setSecondApproval(Boolean secondApproval) {
        this.secondApproval = secondApproval;
    }

    public Boolean isFirstApproval() {
        return firstApproval;
    }

    public void setFirstApproval(Boolean firstApproval) {
        this.firstApproval = firstApproval;
    }

    public Date getDateFirstApproval() {
        return dateFirstApproval;
    }

    public void setDateFirstApproval(Date dateFirstApproval) {
        this.dateFirstApproval = dateFirstApproval;
    }

    public Long getUserFirstApproval() {
        return userFirstApproval;
    }

    public void setUserFirstApproval(Long userFirstApproval) {
        this.userFirstApproval = userFirstApproval;
    }

    public Date getDateSecondApproval() {
        return dateSecondApproval;
    }

    public void setDateSecondApproval(Date dateSecondApproval) {
        this.dateSecondApproval = dateSecondApproval;
    }

    public Long getUserSecondApproval() {
        return userSecondApproval;
    }

    public void setUserSecondApproval(Long userSecondApproval) {
        this.userSecondApproval = userSecondApproval;
    }

    public Boolean isThirdApproval() {
        return thirdApproval;
    }

    public void setThirdApproval(Boolean thirdApproval) {
        this.thirdApproval = thirdApproval;
    }

    public Date getDateThirdApproval() {
        return dateThirdApproval;
    }

    public void setDateThirdApproval(Date dateThirdApproval) {
        this.dateThirdApproval = dateThirdApproval;
    }

    public Long getUserThirdApproval() {
        return userThirdApproval;
    }

    public void setUserThirdApproval(Long userThirdApproval) {
        this.userThirdApproval = userThirdApproval;
    }

    public Boolean isFourthApproval() {
        return fourthApproval;
    }

    public void setFourthApproval(Boolean fourthApproval) {
        this.fourthApproval = fourthApproval;
    }

    public Date getFourthApprovalDate() {
        return fourthApprovalDate;
    }

    public void setFourthApprovalDate(Date fourthApprovalDate) {
        this.fourthApprovalDate = fourthApprovalDate;
    }

    public Long getUserFourthApproval() {
        return userFourthApproval;
    }

    public void setUserFourthApproval(Long userFourthApproval) {
        this.userFourthApproval = userFourthApproval;
    }

    public String getJustificationDisapproval() {
        return justificationDisapproval;
    }

    public void setJustificationDisapproval(String justificationDisapproval) {
        this.justificationDisapproval = justificationDisapproval;
    }
}
