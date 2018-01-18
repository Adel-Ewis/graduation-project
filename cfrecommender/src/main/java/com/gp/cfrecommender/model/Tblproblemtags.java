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
@Table(name = "tblproblemtags", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TagName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblproblemtags.findAll", query = "SELECT t FROM Tblproblemtags t"),
    @NamedQuery(name = "Tblproblemtags.findByTagID", query = "SELECT t FROM Tblproblemtags t WHERE t.tagID = :tagID"),
    @NamedQuery(name = "Tblproblemtags.findByTagName", query = "SELECT t FROM Tblproblemtags t WHERE t.tagName = :tagName"),
    @NamedQuery(name = "Tblproblemtags.findByTagDescription", query = "SELECT t FROM Tblproblemtags t WHERE t.tagDescription = :tagDescription")})
public class Tblproblemtags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TagID", nullable = false)
    private Integer tagID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TagName", nullable = false, length = 100)
    private String tagName;
    @Size(max = 2000)
    @Column(name = "TagDescription", length = 2000)
    private String tagDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tagID", fetch = FetchType.LAZY)
    private List<Tblproblemtagsbridge> tblproblemtagsbridgeList;

    public Tblproblemtags() {
    }

    public Tblproblemtags(Integer tagID) {
        this.tagID = tagID;
    }

    public Tblproblemtags(Integer tagID, String tagName) {
        this.tagID = tagID;
        this.tagName = tagName;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblproblemtagsbridge> getTblproblemtagsbridgeList() {
        return tblproblemtagsbridgeList;
    }

    public void setTblproblemtagsbridgeList(List<Tblproblemtagsbridge> tblproblemtagsbridgeList) {
        this.tblproblemtagsbridgeList = tblproblemtagsbridgeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagID != null ? tagID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblproblemtags)) {
            return false;
        }
        Tblproblemtags other = (Tblproblemtags) object;
        if ((this.tagID == null && other.tagID != null) || (this.tagID != null && !this.tagID.equals(other.tagID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblproblemtags[ tagID=" + tagID + " ]";
    }

}
