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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "tblverdict", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"VerdictName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblverdict.findAll", query = "SELECT t FROM Tblverdict t"),
    @NamedQuery(name = "Tblverdict.findByVerdictID", query = "SELECT t FROM Tblverdict t WHERE t.verdictID = :verdictID"),
    @NamedQuery(name = "Tblverdict.findByVerdictName", query = "SELECT t FROM Tblverdict t WHERE t.verdictName = :verdictName"),
    @NamedQuery(name = "Tblverdict.findByVerdictDescription", query = "SELECT t FROM Tblverdict t WHERE t.verdictDescription = :verdictDescription")})
public class Tblverdict implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VerdictID", nullable = false)
    private Integer verdictID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "VerdictName", nullable = false, length = 100)
    private String verdictName;
    @Size(max = 2000)
    @Column(name = "VerdictDescription", length = 2000)
    private String verdictDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "verdictID", fetch = FetchType.LAZY)
    private List<Tblsubmissions> tblsubmissionsList;

    public Tblverdict() {
    }

    public Tblverdict(Integer verdictID) {
        this.verdictID = verdictID;
    }

    public Tblverdict(Integer verdictID, String verdictName) {
        this.verdictID = verdictID;
        this.verdictName = verdictName;
    }

    public Integer getVerdictID() {
        return verdictID;
    }

    public void setVerdictID(Integer verdictID) {
        this.verdictID = verdictID;
    }

    public String getVerdictName() {
        return verdictName;
    }

    public void setVerdictName(String verdictName) {
        this.verdictName = verdictName;
    }

    public String getVerdictDescription() {
        return verdictDescription;
    }

    public void setVerdictDescription(String verdictDescription) {
        this.verdictDescription = verdictDescription;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsubmissions> getTblsubmissionsList() {
        return tblsubmissionsList;
    }

    public void setTblsubmissionsList(List<Tblsubmissions> tblsubmissionsList) {
        this.tblsubmissionsList = tblsubmissionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (verdictID != null ? verdictID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblverdict)) {
            return false;
        }
        Tblverdict other = (Tblverdict) object;
        if ((this.verdictID == null && other.verdictID != null) || (this.verdictID != null && !this.verdictID.equals(other.verdictID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblverdict[ verdictID=" + verdictID + " ]";
    }

}
