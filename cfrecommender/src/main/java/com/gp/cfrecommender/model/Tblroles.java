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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author mohamed
 */
@Entity
@Table(name = "tblroles", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblroles.findAll", query = "SELECT t FROM Tblroles t"),
    @NamedQuery(name = "Tblroles.findByRoleID", query = "SELECT t FROM Tblroles t WHERE t.roleID = :roleID"),
    @NamedQuery(name = "Tblroles.findByRoleName", query = "SELECT t FROM Tblroles t WHERE t.roleName = :roleName"),
    @NamedQuery(name = "Tblroles.findByRoleDescription", query = "SELECT t FROM Tblroles t WHERE t.roleDescription = :roleDescription")})
public class Tblroles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RoleID", nullable = false)
    private Integer roleID;
    @Size(max = 100)
    @Column(name = "RoleName", length = 100)
    private String roleName;
    @Size(max = 2000)
    @Column(name = "RoleDescription", length = 2000)
    private String roleDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblroles", fetch = FetchType.LAZY)
    private List<Tblpermissions> tblpermissionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "systemUserRoleID", fetch = FetchType.LAZY)
    private List<Tblsystemusers> tblsystemusersList;

    public Tblroles() {
    }

    public Tblroles(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblpermissions> getTblpermissionsList() {
        return tblpermissionsList;
    }

    public void setTblpermissionsList(List<Tblpermissions> tblpermissionsList) {
        this.tblpermissionsList = tblpermissionsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsystemusers> getTblsystemusersList() {
        return tblsystemusersList;
    }

    public void setTblsystemusersList(List<Tblsystemusers> tblsystemusersList) {
        this.tblsystemusersList = tblsystemusersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblroles)) {
            return false;
        }
        Tblroles other = (Tblroles) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblroles[ roleID=" + roleID + " ]";
    }

}
