/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohamed
 */
@Entity
@Table(name = "tblsubmissions", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblsubmissions.findAll", query = "SELECT t FROM Tblsubmissions t"),
    @NamedQuery(name = "Tblsubmissions.findBySubmissionID", query = "SELECT t FROM Tblsubmissions t WHERE t.submissionID = :submissionID"),
    @NamedQuery(name = "Tblsubmissions.findBySubmissionCFID", query = "SELECT t FROM Tblsubmissions t WHERE t.submissionCFID = :submissionCFID"),
    @NamedQuery(name = "Tblsubmissions.findBySubmissionTime", query = "SELECT t FROM Tblsubmissions t WHERE t.submissionTime = :submissionTime"),
    @NamedQuery(name = "Tblsubmissions.findByPassedTestCount", query = "SELECT t FROM Tblsubmissions t WHERE t.passedTestCount = :passedTestCount"),
    @NamedQuery(name = "Tblsubmissions.findByTimeConsumedMillis", query = "SELECT t FROM Tblsubmissions t WHERE t.timeConsumedMillis = :timeConsumedMillis"),
    @NamedQuery(name = "Tblsubmissions.findByMemoryConsumedBytes", query = "SELECT t FROM Tblsubmissions t WHERE t.memoryConsumedBytes = :memoryConsumedBytes"),
    @NamedQuery(name = "Tblsubmissions.findBySubmissionURL", query = "SELECT t FROM Tblsubmissions t WHERE t.submissionURL = :submissionURL")})
public class Tblsubmissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SubmissionID", nullable = false)
    private Long submissionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SubmissionCFID", nullable = false)
    private long submissionCFID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SubmissionTime", nullable = false)
    private long submissionTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PassedTestCount", nullable = false)
    private int passedTestCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TimeConsumedMillis", nullable = false)
    private long timeConsumedMillis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MemoryConsumedBytes", nullable = false)
    private long memoryConsumedBytes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "SubmissionURL", nullable = false, length = 300)
    private String submissionURL;
    @JoinColumn(name = "ContestID", referencedColumnName = "ContestID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblcontests contestID;
    @JoinColumn(name = "PLanguageID", referencedColumnName = "PLID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblprogramminglanguage pLanguageID;
    @JoinColumn(name = "ProblemID", referencedColumnName = "ProblemID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblproblems problemID;
    @JoinColumn(name = "TestSetID", referencedColumnName = "TestSetID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tbltestset testSetID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblusers userID;
    @JoinColumn(name = "VerdictID", referencedColumnName = "VerdictID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblverdict verdictID;

    public Tblsubmissions() {
    }

    public Tblsubmissions(Long submissionID) {
        this.submissionID = submissionID;
    }

    public Tblsubmissions(Long submissionID, long submissionCFID, long submissionTime, int passedTestCount, long timeConsumedMillis, long memoryConsumedBytes, String submissionURL) {
        this.submissionID = submissionID;
        this.submissionCFID = submissionCFID;
        this.submissionTime = submissionTime;
        this.passedTestCount = passedTestCount;
        this.timeConsumedMillis = timeConsumedMillis;
        this.memoryConsumedBytes = memoryConsumedBytes;
        this.submissionURL = submissionURL;
    }

    public Long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Long submissionID) {
        this.submissionID = submissionID;
    }

    public long getSubmissionCFID() {
        return submissionCFID;
    }

    public void setSubmissionCFID(long submissionCFID) {
        this.submissionCFID = submissionCFID;
    }

    public long getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(long submissionTime) {
        this.submissionTime = submissionTime;
    }

    public int getPassedTestCount() {
        return passedTestCount;
    }

    public void setPassedTestCount(int passedTestCount) {
        this.passedTestCount = passedTestCount;
    }

    public long getTimeConsumedMillis() {
        return timeConsumedMillis;
    }

    public void setTimeConsumedMillis(long timeConsumedMillis) {
        this.timeConsumedMillis = timeConsumedMillis;
    }

    public long getMemoryConsumedBytes() {
        return memoryConsumedBytes;
    }

    public void setMemoryConsumedBytes(long memoryConsumedBytes) {
        this.memoryConsumedBytes = memoryConsumedBytes;
    }

    public String getSubmissionURL() {
        return submissionURL;
    }

    public void setSubmissionURL(String submissionURL) {
        this.submissionURL = submissionURL;
    }

    public Tblcontests getContestID() {
        return contestID;
    }

    public void setContestID(Tblcontests contestID) {
        this.contestID = contestID;
    }

    public Tblprogramminglanguage getPLanguageID() {
        return pLanguageID;
    }

    public void setPLanguageID(Tblprogramminglanguage pLanguageID) {
        this.pLanguageID = pLanguageID;
    }

    public Tblproblems getProblemID() {
        return problemID;
    }

    public void setProblemID(Tblproblems problemID) {
        this.problemID = problemID;
    }

    public Tbltestset getTestSetID() {
        return testSetID;
    }

    public void setTestSetID(Tbltestset testSetID) {
        this.testSetID = testSetID;
    }

    public Tblusers getUserID() {
        return userID;
    }

    public void setUserID(Tblusers userID) {
        this.userID = userID;
    }

    public Tblverdict getVerdictID() {
        return verdictID;
    }

    public void setVerdictID(Tblverdict verdictID) {
        this.verdictID = verdictID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (submissionID != null ? submissionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblsubmissions)) {
            return false;
        }
        Tblsubmissions other = (Tblsubmissions) object;
        if ((this.submissionID == null && other.submissionID != null) || (this.submissionID != null && !this.submissionID.equals(other.submissionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblsubmissions[ submissionID=" + submissionID + " ]";
    }

}
