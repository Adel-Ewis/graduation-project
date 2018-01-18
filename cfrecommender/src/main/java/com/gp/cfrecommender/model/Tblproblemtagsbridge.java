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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohamed
 */
@Entity
@Table(name = "tblproblemtagsbridge", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ProblemID", "TagID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproblemtagsbridge.findAll", query = "SELECT t FROM Tblproblemtagsbridge t"),
    @NamedQuery(name = "Tblproblemtagsbridge.findByPtid", query = "SELECT t FROM Tblproblemtagsbridge t WHERE t.ptid = :ptid")})
public class Tblproblemtagsbridge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PTID", nullable = false)
    private Long ptid;
    @JoinColumn(name = "ProblemID", referencedColumnName = "ProblemID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblproblems problemID;
    @JoinColumn(name = "TagID", referencedColumnName = "TagID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblproblemtags tagID;

    public Tblproblemtagsbridge() {
    }

    public Tblproblemtagsbridge(Long ptid) {
        this.ptid = ptid;
    }

    public Long getPtid() {
        return ptid;
    }

    public void setPtid(Long ptid) {
        this.ptid = ptid;
    }

    public Tblproblems getProblemID() {
        return problemID;
    }

    public void setProblemID(Tblproblems problemID) {
        this.problemID = problemID;
    }

    public Tblproblemtags getTagID() {
        return tagID;
    }

    public void setTagID(Tblproblemtags tagID) {
        this.tagID = tagID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptid != null ? ptid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblproblemtagsbridge)) {
            return false;
        }
        Tblproblemtagsbridge other = (Tblproblemtagsbridge) object;
        if ((this.ptid == null && other.ptid != null) || (this.ptid != null && !this.ptid.equals(other.ptid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblproblemtagsbridge[ ptid=" + ptid + " ]";
    }

}
