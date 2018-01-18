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
@Table(name = "tblcontesttypes", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TypeName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblcontesttypes.findAll", query = "SELECT t FROM Tblcontesttypes t"),
    @NamedQuery(name = "Tblcontesttypes.findByTypeID", query = "SELECT t FROM Tblcontesttypes t WHERE t.typeID = :typeID"),
    @NamedQuery(name = "Tblcontesttypes.findByTypeName", query = "SELECT t FROM Tblcontesttypes t WHERE t.typeName = :typeName"),
    @NamedQuery(name = "Tblcontesttypes.findByTypeDescription", query = "SELECT t FROM Tblcontesttypes t WHERE t.typeDescription = :typeDescription")})
public class Tblcontesttypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TypeID", nullable = false)
    private Integer typeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TypeName", nullable = false, length = 100)
    private String typeName;
    @Size(max = 2000)
    @Column(name = "TypeDescription", length = 2000)
    private String typeDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contestType", fetch = FetchType.LAZY)
    private List<Tblcontests> tblcontestsList;

    public Tblcontesttypes() {
    }

    public Tblcontesttypes(Integer typeID) {
        this.typeID = typeID;
    }

    public Tblcontesttypes(Integer typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
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
        hash += (typeID != null ? typeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblcontesttypes)) {
            return false;
        }
        Tblcontesttypes other = (Tblcontesttypes) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblcontesttypes[ typeID=" + typeID + " ]";
    }

}
