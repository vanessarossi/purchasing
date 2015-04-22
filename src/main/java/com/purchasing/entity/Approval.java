package com.purchasing.entity;

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

    @Column(name = "user_first_approval")
    private String userFirstApproval;

    @Column(name = "second_approval")
    private Boolean secondApproval;

    @Column(name = "date_second_approval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSecondApproval;

    @Column(name = "user_second_approval")
    private String userSecondApproval;

    @Column(name = "third_approval")
    private Boolean thirdApproval;

    @Column(name = "date_third_approval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateThirdApproval;

    @Column(name = "user_third_approval")
    private String userThirdApproval;

    @Column(name = "fourth_approval")
    private Boolean fourthApproval;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_fourth_approval")
    private Date dateFourthApproval;

    @Column(name = "user_fourth_approval")
    private String userFourthApproval;

    @Column(name = "justification_disapproval")
    private String justificationDisapproval;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFirstApproval() {
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

    public String getUserFirstApproval() {
        return userFirstApproval;
    }

    public void setUserFirstApproval(String userFirstApproval) {
        this.userFirstApproval = userFirstApproval;
    }

    public Boolean getSecondApproval() {
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

    public String getUserSecondApproval() {
        return userSecondApproval;
    }

    public void setUserSecondApproval(String userSecondApproval) {
        this.userSecondApproval = userSecondApproval;
    }

    public Boolean getThirdApproval() {
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

    public String getUserThirdApproval() {
        return userThirdApproval;
    }

    public void setUserThirdApproval(String userThirdApproval) {
        this.userThirdApproval = userThirdApproval;
    }

    public Boolean getFourthApproval() {
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

    public String getUserFourthApproval() {
        return userFourthApproval;
    }

    public void setUserFourthApproval(String userFourthApproval) {
        this.userFourthApproval = userFourthApproval;
    }

    public String getJustificationDisapproval() {
        return justificationDisapproval;
    }

    public void setJustificationDisapproval(String justificationDisapproval) {
        this.justificationDisapproval = justificationDisapproval;
    }
}
