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
@Table(name = "tbltestset", catalog = "cforces", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TestSetName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbltestset.findAll", query = "SELECT t FROM Tbltestset t"),
    @NamedQuery(name = "Tbltestset.findByTestSetID", query = "SELECT t FROM Tbltestset t WHERE t.testSetID = :testSetID"),
    @NamedQuery(name = "Tbltestset.findByTestSetName", query = "SELECT t FROM Tbltestset t WHERE t.testSetName = :testSetName"),
    @NamedQuery(name = "Tbltestset.findByTestSetDescription", query = "SELECT t FROM Tbltestset t WHERE t.testSetDescription = :testSetDescription")})
public class Tbltestset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TestSetID", nullable = false)
    private Integer testSetID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TestSetName", nullable = false, length = 100)
    private String testSetName;
    @Size(max = 2000)
    @Column(name = "TestSetDescription", length = 2000)
    private String testSetDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testSetID", fetch = FetchType.LAZY)
    private List<Tblsubmissions> tblsubmissionsList;

    public Tbltestset() {
    }

    public Tbltestset(Integer testSetID) {
        this.testSetID = testSetID;
    }

    public Tbltestset(Integer testSetID, String testSetName) {
        this.testSetID = testSetID;
        this.testSetName = testSetName;
    }

    public Integer getTestSetID() {
        return testSetID;
    }

    public void setTestSetID(Integer testSetID) {
        this.testSetID = testSetID;
    }

    public String getTestSetName() {
        return testSetName;
    }

    public void setTestSetName(String testSetName) {
        this.testSetName = testSetName;
    }

    public String getTestSetDescription() {
        return testSetDescription;
    }

    public void setTestSetDescription(String testSetDescription) {
        this.testSetDescription = testSetDescription;
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
        hash += (testSetID != null ? testSetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbltestset)) {
            return false;
        }
        Tbltestset other = (Tbltestset) object;
        if ((this.testSetID == null && other.testSetID != null) || (this.testSetID != null && !this.testSetID.equals(other.testSetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tbltestset[ testSetID=" + testSetID + " ]";
    }

}
