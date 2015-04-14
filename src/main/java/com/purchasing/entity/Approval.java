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

    @Column(name = "first_approval")
    private Boolean firstApproval;

    @Column(name = "date_first_approval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFirstApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_first_approval")
    private Long userFirstApproval;

    @Column(name = "second_approval")
    private Boolean secondApproval;

    @Column(name = "date_second_approval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSecondApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_second_approval")
    private Long userSecondApproval;

    @Column(name = "third_approval")
    private Boolean thirdApproval;

    @Column(name = "date_third_approval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateThirdApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_third_approval")
    private Long userThirdApproval;

    @Column(name = "fourth_approval")
    private Boolean fourthApproval;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_fourth_approval")
    private Date dateFourthApproval;

    @Length(min = 1, max = 20)
    @Column(name = "user_fourth_approval")
    private Long userFourthApproval;

    @Column(name = "justification_disapproval")
    private String justificationDisapproval;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean isSecondApproval() {
        return secondApproval;
    }

    public void setSecondApproval(Boolean secondApproval) {
        this.secondApproval = secondApproval;
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

    public Date getDateFourthApproval() {
        return dateFourthApproval;
    }

    public void setDateFourthApproval(Date dateFourthApproval) {
        this.dateFourthApproval = dateFourthApproval;
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
