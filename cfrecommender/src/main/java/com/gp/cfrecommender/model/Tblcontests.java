/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "tblcontests", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcontests.findAll", query = "SELECT t FROM Tblcontests t"),
    @NamedQuery(name = "Tblcontests.findByContestID", query = "SELECT t FROM Tblcontests t WHERE t.contestID = :contestID"),
    @NamedQuery(name = "Tblcontests.findByContestCFID", query = "SELECT t FROM Tblcontests t WHERE t.contestCFID = :contestCFID"),
    @NamedQuery(name = "Tblcontests.findByContestName", query = "SELECT t FROM Tblcontests t WHERE t.contestName = :contestName"),
    @NamedQuery(name = "Tblcontests.findByIsFrozen", query = "SELECT t FROM Tblcontests t WHERE t.isFrozen = :isFrozen"),
    @NamedQuery(name = "Tblcontests.findByContestDurationInSeconds", query = "SELECT t FROM Tblcontests t WHERE t.contestDurationInSeconds = :contestDurationInSeconds"),
    @NamedQuery(name = "Tblcontests.findByContestStartTime", query = "SELECT t FROM Tblcontests t WHERE t.contestStartTime = :contestStartTime"),
    @NamedQuery(name = "Tblcontests.findByContestURL", query = "SELECT t FROM Tblcontests t WHERE t.contestURL = :contestURL"),
    @NamedQuery(name = "Tblcontests.findByContestDifficulty", query = "SELECT t FROM Tblcontests t WHERE t.contestDifficulty = :contestDifficulty"),
    @NamedQuery(name = "Tblcontests.findByContestKind", query = "SELECT t FROM Tblcontests t WHERE t.contestKind = :contestKind"),
    @NamedQuery(name = "Tblcontests.findByICPCRegion", query = "SELECT t FROM Tblcontests t WHERE t.iCPCRegion = :iCPCRegion"),
    @NamedQuery(name = "Tblcontests.findByContestCountry", query = "SELECT t FROM Tblcontests t WHERE t.contestCountry = :contestCountry")})
public class Tblcontests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ContestID", nullable = false)
    private Integer contestID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ContestCFID", nullable = false)
    private int contestCFID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ContestName", nullable = false, length = 500)
    private String contestName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsFrozen", nullable = false)
    private int isFrozen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ContestDurationInSeconds", nullable = false)
    private long contestDurationInSeconds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ContestStartTime", nullable = false)
    private long contestStartTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ContestURL", nullable = false, length = 500)
    private String contestURL;
    @Column(name = "ContestDifficulty")
    private Integer contestDifficulty;
    @Size(max = 200)
    @Column(name = "ContestKind", length = 200)
    private String contestKind;
    @Size(max = 200)
    @Column(name = "ICPCRegion", length = 200)
    private String iCPCRegion;
    @Size(max = 200)
    @Column(name = "ContestCountry", length = 200)
    private String contestCountry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contestID", fetch = FetchType.LAZY)
    private List<Tblsubmissions> tblsubmissionsList;
    @JoinColumn(name = "ContestPhase", referencedColumnName = "PhaseID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblcontestphases contestPhase;
    @JoinColumn(name = "ContestType", referencedColumnName = "TypeID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblcontesttypes contestType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contestID", fetch = FetchType.LAZY)
    private List<Tblproblems> tblproblemsList;

    public Tblcontests() {
    }

    public Tblcontests(Integer contestID) {
        this.contestID = contestID;
    }

    public Tblcontests(Integer contestID, int contestCFID, String contestName, int isFrozen, long contestDurationInSeconds, long contestStartTime, String contestURL) {
        this.contestID = contestID;
        this.contestCFID = contestCFID;
        this.contestName = contestName;
        this.isFrozen = isFrozen;
        this.contestDurationInSeconds = contestDurationInSeconds;
        this.contestStartTime = contestStartTime;
        this.contestURL = contestURL;
    }

    public Integer getContestID() {
        return contestID;
    }

    public void setContestID(Integer contestID) {
        this.contestID = contestID;
    }

    public int getContestCFID() {
        return contestCFID;
    }

    public void setContestCFID(int contestCFID) {
        this.contestCFID = contestCFID;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public int getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(int isFrozen) {
        this.isFrozen = isFrozen;
    }

    public long getContestDurationInSeconds() {
        return contestDurationInSeconds;
    }

    public void setContestDurationInSeconds(long contestDurationInSeconds) {
        this.contestDurationInSeconds = contestDurationInSeconds;
    }

    public long getContestStartTime() {
        return contestStartTime;
    }

    public void setContestStartTime(long contestStartTime) {
        this.contestStartTime = contestStartTime;
    }

    public String getContestURL() {
        return contestURL;
    }

    public void setContestURL(String contestURL) {
        this.contestURL = contestURL;
    }

    public Integer getContestDifficulty() {
        return contestDifficulty;
    }

    public void setContestDifficulty(Integer contestDifficulty) {
        this.contestDifficulty = contestDifficulty;
    }

    public String getContestKind() {
        return contestKind;
    }

    public void setContestKind(String contestKind) {
        this.contestKind = contestKind;
    }

    public String getICPCRegion() {
        return iCPCRegion;
    }

    public void setICPCRegion(String iCPCRegion) {
        this.iCPCRegion = iCPCRegion;
    }

    public String getContestCountry() {
        return contestCountry;
    }

    public void setContestCountry(String contestCountry) {
        this.contestCountry = contestCountry;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsubmissions> getTblsubmissionsList() {
        return tblsubmissionsList;
    }

    public void setTblsubmissionsList(List<Tblsubmissions> tblsubmissionsList) {
        this.tblsubmissionsList = tblsubmissionsList;
    }

    public Tblcontestphases getContestPhase() {
        return contestPhase;
    }

    public void setContestPhase(Tblcontestphases contestPhase) {
        this.contestPhase = contestPhase;
    }

    public Tblcontesttypes getContestType() {
        return contestType;
    }

    public void setContestType(Tblcontesttypes contestType) {
        this.contestType = contestType;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblproblems> getTblproblemsList() {
        return tblproblemsList;
    }

    public void setTblproblemsList(List<Tblproblems> tblproblemsList) {
        this.tblproblemsList = tblproblemsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contestID != null ? contestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcontests)) {
            return false;
        }
        Tblcontests other = (Tblcontests) object;
        if ((this.contestID == null && other.contestID != null) || (this.contestID != null && !this.contestID.equals(other.contestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblcontests[ contestID=" + contestID + " ]";
    }

}
