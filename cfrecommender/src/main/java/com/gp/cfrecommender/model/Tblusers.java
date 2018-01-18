/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author mohamed
 */
@Entity
@Table(name = "tblusers", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"UserHandle"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblusers.findAll", query = "SELECT t FROM Tblusers t"),
    @NamedQuery(name = "Tblusers.findByUserID", query = "SELECT t FROM Tblusers t WHERE t.userID = :userID"),
    @NamedQuery(name = "Tblusers.findByUserRating", query = "SELECT t FROM Tblusers t WHERE t.userRating = :userRating"),
    @NamedQuery(name = "Tblusers.findByUserHandle", query = "SELECT t FROM Tblusers t WHERE t.userHandle = :userHandle"),
    @NamedQuery(name = "Tblusers.findByUserProfile", query = "SELECT t FROM Tblusers t WHERE t.userProfile = :userProfile"),
    @NamedQuery(name = "Tblusers.findByUserNOFParticipations", query = "SELECT t FROM Tblusers t WHERE t.userNOFParticipations = :userNOFParticipations"),
    @NamedQuery(name = "Tblusers.findByUserPoints", query = "SELECT t FROM Tblusers t WHERE t.userPoints = :userPoints"),
    @NamedQuery(name = "Tblusers.findByCountry", query = "SELECT t FROM Tblusers t WHERE t.country = :country"),
    @NamedQuery(name = "Tblusers.findByContribution", query = "SELECT t FROM Tblusers t WHERE t.contribution = :contribution"),
    @NamedQuery(name = "Tblusers.findByUserRank", query = "SELECT t FROM Tblusers t WHERE t.userRank = :userRank"),
    @NamedQuery(name = "Tblusers.findByMaxRank", query = "SELECT t FROM Tblusers t WHERE t.maxRank = :maxRank"),
    @NamedQuery(name = "Tblusers.findByMaxRating", query = "SELECT t FROM Tblusers t WHERE t.maxRating = :maxRating"),
    @NamedQuery(name = "Tblusers.findByRegistrationTimeInSeconds", query = "SELECT t FROM Tblusers t WHERE t.registrationTimeInSeconds = :registrationTimeInSeconds"),
    @NamedQuery(name = "Tblusers.findBySubmissionsLastUpdate", query = "SELECT t FROM Tblusers t WHERE t.submissionsLastUpdate = :submissionsLastUpdate")})
public class Tblusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserID", nullable = false)
    private Long userID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserRating", nullable = false)
    private int userRating;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "UserHandle", nullable = false, length = 200)
    private String userHandle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "UserProfile", nullable = false, length = 1000)
    private String userProfile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserNOFParticipations", nullable = false)
    private int userNOFParticipations;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserPoints", nullable = false)
    private int userPoints;
    @Size(max = 200)
    @Column(name = "Country", length = 200)
    private String country;
    @Column(name = "Contribution")
    private Integer contribution;
    @Size(max = 1000)
    @Column(name = "UserRank", length = 1000)
    private String userRank;
    @Size(max = 1000)
    @Column(name = "MaxRank", length = 1000)
    private String maxRank;
    @Column(name = "MaxRating")
    private Integer maxRating;
    @Column(name = "RegistrationTimeInSeconds")
    private BigInteger registrationTimeInSeconds;
    @Column(name = "SubmissionsLastUpdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionsLastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID", fetch = FetchType.LAZY)
    private List<Tblsubmissions> tblsubmissionsList;
    @OneToMany(mappedBy = "userHandle", fetch = FetchType.LAZY)
    private List<Tblsystemusers> tblsystemusersList;

    public Tblusers() {
    }

    public Tblusers(Long userID) {
        this.userID = userID;
    }

    public Tblusers(Long userID, int userRating, String userHandle, String userProfile, int userNOFParticipations, int userPoints) {
        this.userID = userID;
        this.userRating = userRating;
        this.userHandle = userHandle;
        this.userProfile = userProfile;
        this.userNOFParticipations = userNOFParticipations;
        this.userPoints = userPoints;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public int getUserNOFParticipations() {
        return userNOFParticipations;
    }

    public void setUserNOFParticipations(int userNOFParticipations) {
        this.userNOFParticipations = userNOFParticipations;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public Integer getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Integer maxRating) {
        this.maxRating = maxRating;
    }

    public BigInteger getRegistrationTimeInSeconds() {
        return registrationTimeInSeconds;
    }

    public void setRegistrationTimeInSeconds(BigInteger registrationTimeInSeconds) {
        this.registrationTimeInSeconds = registrationTimeInSeconds;
    }

    public Date getSubmissionsLastUpdate() {
        return submissionsLastUpdate;
    }

    public void setSubmissionsLastUpdate(Date submissionsLastUpdate) {
        this.submissionsLastUpdate = submissionsLastUpdate;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsubmissions> getTblsubmissionsList() {
        return tblsubmissionsList;
    }

    public void setTblsubmissionsList(List<Tblsubmissions> tblsubmissionsList) {
        this.tblsubmissionsList = tblsubmissionsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsystemusers> getTblsystemusersList() {
        return tblsystemusersList;
    }

    public void setTblsystemusersList(List<Tblsystemusers> tblsystemusersList) {
        this.tblsystemusersList = tblsystemusersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblusers)) {
            return false;
        }
        Tblusers other = (Tblusers) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblusers[ userID=" + userID + " ]";
    }

}
