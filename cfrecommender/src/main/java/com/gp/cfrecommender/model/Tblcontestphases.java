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
@Table(name = "tblcontestphases", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PhaseName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcontestphases.findAll", query = "SELECT t FROM Tblcontestphases t"),
    @NamedQuery(name = "Tblcontestphases.findByPhaseID", query = "SELECT t FROM Tblcontestphases t WHERE t.phaseID = :phaseID"),
    @NamedQuery(name = "Tblcontestphases.findByPhaseName", query = "SELECT t FROM Tblcontestphases t WHERE t.phaseName = :phaseName"),
    @NamedQuery(name = "Tblcontestphases.findByPhaseDescription", query = "SELECT t FROM Tblcontestphases t WHERE t.phaseDescription = :phaseDescription")})
public class Tblcontestphases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PhaseID", nullable = false)
    private Integer phaseID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PhaseName", nullable = false, length = 100)
    private String phaseName;
    @Size(max = 2000)
    @Column(name = "PhaseDescription", length = 2000)
    private String phaseDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contestPhase", fetch = FetchType.LAZY)
    private List<Tblcontests> tblcontestsList;

    public Tblcontestphases() {
    }

    public Tblcontestphases(Integer phaseID) {
        this.phaseID = phaseID;
    }

    public Tblcontestphases(Integer phaseID, String phaseName) {
        this.phaseID = phaseID;
        this.phaseName = phaseName;
    }

    public Integer getPhaseID() {
        return phaseID;
    }

    public void setPhaseID(Integer phaseID) {
        this.phaseID = phaseID;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getPhaseDescription() {
        return phaseDescription;
    }

    public void setPhaseDescription(String phaseDescription) {
        this.phaseDescription = phaseDescription;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblcontests> getTblcontestsList() {
        return tblcontestsList;
    }

    public void setTblcontestsList(List<Tblcontests> tblcontestsList) {
        this.tblcontestsList = tblcontestsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phaseID != null ? phaseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcontestphases)) {
            return false;
        }
        Tblcontestphases other = (Tblcontestphases) object;
        if ((this.phaseID == null && other.phaseID != null) || (this.phaseID != null && !this.phaseID.equals(other.phaseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblcontestphases[ phaseID=" + phaseID + " ]";
    }

}
