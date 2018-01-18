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
@Table(name = "tblproblems", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproblems.findAll", query = "SELECT t FROM Tblproblems t"),
    @NamedQuery(name = "Tblproblems.findByProblemID", query = "SELECT t FROM Tblproblems t WHERE t.problemID = :problemID"),
    @NamedQuery(name = "Tblproblems.findByProblemIndex", query = "SELECT t FROM Tblproblems t WHERE t.problemIndex = :problemIndex"),
    @NamedQuery(name = "Tblproblems.findByProblemName", query = "SELECT t FROM Tblproblems t WHERE t.problemName = :problemName"),
    @NamedQuery(name = "Tblproblems.findByProblemPoints", query = "SELECT t FROM Tblproblems t WHERE t.problemPoints = :problemPoints"),
    @NamedQuery(name = "Tblproblems.findBySolvedCount", query = "SELECT t FROM Tblproblems t WHERE t.solvedCount = :solvedCount"),
    @NamedQuery(name = "Tblproblems.findByProblemURL", query = "SELECT t FROM Tblproblems t WHERE t.problemURL = :problemURL")})
public class Tblproblems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProblemID", nullable = false)
    private Long problemID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ProblemIndex", nullable = false, length = 200)
    private String problemIndex;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ProblemName", nullable = false, length = 1000)
    private String problemName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ProblemPoints", precision = 22)
    private Double problemPoints;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SolvedCount", nullable = false)
    private int solvedCount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "ProblemURL", nullable = false, length = 1000)
    private String problemURL;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problemID", fetch = FetchType.LAZY)
    private List<Tblsubmissions> tblsubmissionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "problemID", fetch = FetchType.LAZY)
    private List<Tblproblemtagsbridge> tblproblemtagsbridgeList;
    @JoinColumn(name = "ContestID", referencedColumnName = "ContestID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblcontests contestID;
    @JoinColumn(name = "ProblemType", referencedColumnName = "TypeID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblproblemtypes problemType;

    public Tblproblems() {
    }

    public Tblproblems(Long problemID) {
        this.problemID = problemID;
    }

    public Tblproblems(Long problemID, String problemIndex, String problemName, int solvedCount, String problemURL) {
        this.problemID = problemID;
        this.problemIndex = problemIndex;
        this.problemName = problemName;
        this.solvedCount = solvedCount;
        this.problemURL = problemURL;
    }

    public Long getProblemID() {
        return problemID;
    }

    public void setProblemID(Long problemID) {
        this.problemID = problemID;
    }

    public String getProblemIndex() {
        return problemIndex;
    }

    public void setProblemIndex(String problemIndex) {
        this.problemIndex = problemIndex;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public Double getProblemPoints() {
        return problemPoints;
    }

    public void setProblemPoints(Double problemPoints) {
        this.problemPoints = problemPoints;
    }

    public int getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(int solvedCount) {
        this.solvedCount = solvedCount;
    }

    public String getProblemURL() {
        return problemURL;
    }

    public void setProblemURL(String problemURL) {
        this.problemURL = problemURL;
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
    public List<Tblproblemtagsbridge> getTblproblemtagsbridgeList() {
        return tblproblemtagsbridgeList;
    }

    public void setTblproblemtagsbridgeList(List<Tblproblemtagsbridge> tblproblemtagsbridgeList) {
        this.tblproblemtagsbridgeList = tblproblemtagsbridgeList;
    }

    public Tblcontests getContestID() {
        return contestID;
    }

    public void setContestID(Tblcontests contestID) {
        this.contestID = contestID;
    }

    public Tblproblemtypes getProblemType() {
        return problemType;
    }

    public void setProblemType(Tblproblemtypes problemType) {
        this.problemType = problemType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (problemID != null ? problemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblproblems)) {
            return false;
        }
        Tblproblems other = (Tblproblems) object;
        if ((this.problemID == null && other.problemID != null) || (this.problemID != null && !this.problemID.equals(other.problemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblproblems[ problemID=" + problemID + " ]";
    }

}
