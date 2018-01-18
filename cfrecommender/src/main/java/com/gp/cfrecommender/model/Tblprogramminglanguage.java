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
@Table(name = "tblprogramminglanguage", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PLName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblprogramminglanguage.findAll", query = "SELECT t FROM Tblprogramminglanguage t"),
    @NamedQuery(name = "Tblprogramminglanguage.findByPlid", query = "SELECT t FROM Tblprogramminglanguage t WHERE t.plid = :plid"),
    @NamedQuery(name = "Tblprogramminglanguage.findByPLName", query = "SELECT t FROM Tblprogramminglanguage t WHERE t.pLName = :pLName"),
    @NamedQuery(name = "Tblprogramminglanguage.findByPLDescription", query = "SELECT t FROM Tblprogramminglanguage t WHERE t.pLDescription = :pLDescription")})
public class Tblprogramminglanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLID", nullable = false)
    private Integer plid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PLName", nullable = false, length = 100)
    private String pLName;
    @Size(max = 2000)
    @Column(name = "PLDescription", length = 2000)
    private String pLDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pLanguageID", fetch = FetchType.LAZY)
    private List<Tblsubmissions> tblsubmissionsList;

    public Tblprogramminglanguage() {
    }

    public Tblprogramminglanguage(Integer plid) {
        this.plid = plid;
    }

    public Tblprogramminglanguage(Integer plid, String pLName) {
        this.plid = plid;
        this.pLName = pLName;
    }

    public Integer getPlid() {
        return plid;
    }

    public void setPlid(Integer plid) {
        this.plid = plid;
    }

    public String getPLName() {
        return pLName;
    }

    public void setPLName(String pLName) {
        this.pLName = pLName;
    }

    public String getPLDescription() {
        return pLDescription;
    }

    public void setPLDescription(String pLDescription) {
        this.pLDescription = pLDescription;
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
        hash += (plid != null ? plid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblprogramminglanguage)) {
            return false;
        }
        Tblprogramminglanguage other = (Tblprogramminglanguage) object;
        if ((this.plid == null && other.plid != null) || (this.plid != null && !this.plid.equals(other.plid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblprogramminglanguage[ plid=" + plid + " ]";
    }

}
