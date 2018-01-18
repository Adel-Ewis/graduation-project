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
@Table(name = "tblsystemusers", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblsystemusers.findAll", query = "SELECT t FROM Tblsystemusers t"),
    @NamedQuery(name = "Tblsystemusers.findBySystemUserID", query = "SELECT t FROM Tblsystemusers t WHERE t.systemUserID = :systemUserID"),
    @NamedQuery(name = "Tblsystemusers.findBySystemUserFirstName", query = "SELECT t FROM Tblsystemusers t WHERE t.systemUserFirstName = :systemUserFirstName"),
    @NamedQuery(name = "Tblsystemusers.findBySystemUserLastName", query = "SELECT t FROM Tblsystemusers t WHERE t.systemUserLastName = :systemUserLastName"),
    @NamedQuery(name = "Tblsystemusers.findBySystemUserEmail", query = "SELECT t FROM Tblsystemusers t WHERE t.systemUserEmail = :systemUserEmail"),
    @NamedQuery(name = "Tblsystemusers.findByUserName", query = "SELECT t FROM Tblsystemusers t WHERE t.userName = :userName"),
    @NamedQuery(name = "Tblsystemusers.findByPassword", query = "SELECT t FROM Tblsystemusers t WHERE t.password = :password"),
    @NamedQuery(name = "Tblsystemusers.findByIsActive", query = "SELECT t FROM Tblsystemusers t WHERE t.isActive = :isActive")})
public class Tblsystemusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SystemUserID", nullable = false)
    private Long systemUserID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SystemUserFirstName", nullable = false, length = 50)
    private String systemUserFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SystemUserLastName", nullable = false, length = 50)
    private String systemUserLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SystemUserEmail", nullable = false, length = 100)
    private String systemUserEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password", nullable = false, length = 50)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive", nullable = false)
    private int isActive;
    @JoinColumn(name = "SystemUserRoleID", referencedColumnName = "RoleID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblroles systemUserRoleID;
    @JoinColumn(name = "UserHandle", referencedColumnName = "UserID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tblusers userHandle;

    public Tblsystemusers() {
    }

    public Tblsystemusers(Long systemUserID) {
        this.systemUserID = systemUserID;
    }

    public Tblsystemusers(Long systemUserID, String systemUserFirstName, String systemUserLastName, String systemUserEmail, String userName, String password, int isActive) {
        this.systemUserID = systemUserID;
        this.systemUserFirstName = systemUserFirstName;
        this.systemUserLastName = systemUserLastName;
        this.systemUserEmail = systemUserEmail;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    public Long getSystemUserID() {
        return systemUserID;
    }

    public void setSystemUserID(Long systemUserID) {
        this.systemUserID = systemUserID;
    }

    public String getSystemUserFirstName() {
        return systemUserFirstName;
    }

    public void setSystemUserFirstName(String systemUserFirstName) {
        this.systemUserFirstName = systemUserFirstName;
    }

    public String getSystemUserLastName() {
        return systemUserLastName;
    }

    public void setSystemUserLastName(String systemUserLastName) {
        this.systemUserLastName = systemUserLastName;
    }

    public String getSystemUserEmail() {
        return systemUserEmail;
    }

    public void setSystemUserEmail(String systemUserEmail) {
        this.systemUserEmail = systemUserEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Tblroles getSystemUserRoleID() {
        return systemUserRoleID;
    }

    public void setSystemUserRoleID(Tblroles systemUserRoleID) {
        this.systemUserRoleID = systemUserRoleID;
    }

    public Tblusers getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(Tblusers userHandle) {
        this.userHandle = userHandle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (systemUserID != null ? systemUserID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblsystemusers)) {
            return false;
        }
        Tblsystemusers other = (Tblsystemusers) object;
        if ((this.systemUserID == null && other.systemUserID != null) || (this.systemUserID != null && !this.systemUserID.equals(other.systemUserID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblsystemusers[ systemUserID=" + systemUserID + " ]";
    }

}
