/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohamed
 */
@Entity
@Table(name = "tblpermissions", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblpermissions.findAll", query = "SELECT t FROM Tblpermissions t"),
    @NamedQuery(name = "Tblpermissions.findByPageID", query = "SELECT t FROM Tblpermissions t WHERE t.tblpermissionsPK.pageID = :pageID"),
    @NamedQuery(name = "Tblpermissions.findByRoleID", query = "SELECT t FROM Tblpermissions t WHERE t.tblpermissionsPK.roleID = :roleID"),
    @NamedQuery(name = "Tblpermissions.findByViewPermission", query = "SELECT t FROM Tblpermissions t WHERE t.viewPermission = :viewPermission")})
public class Tblpermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblpermissionsPK tblpermissionsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ViewPermission", nullable = false)
    private int viewPermission;
    @JoinColumn(name = "PageID", referencedColumnName = "PageID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblpages tblpages;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblroles tblroles;

    public Tblpermissions() {
    }

    public Tblpermissions(TblpermissionsPK tblpermissionsPK) {
        this.tblpermissionsPK = tblpermissionsPK;
    }

    public Tblpermissions(TblpermissionsPK tblpermissionsPK, int viewPermission) {
        this.tblpermissionsPK = tblpermissionsPK;
        this.viewPermission = viewPermission;
    }

    public Tblpermissions(int pageID, int roleID) {
        this.tblpermissionsPK = new TblpermissionsPK(pageID, roleID);
    }

    public TblpermissionsPK getTblpermissionsPK() {
        return tblpermissionsPK;
    }

    public void setTblpermissionsPK(TblpermissionsPK tblpermissionsPK) {
        this.tblpermissionsPK = tblpermissionsPK;
    }

    public int getViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(int viewPermission) {
        this.viewPermission = viewPermission;
    }

    public Tblpages getTblpages() {
        return tblpages;
    }

    public void setTblpages(Tblpages tblpages) {
        this.tblpages = tblpages;
    }

    public Tblroles getTblroles() {
        return tblroles;
    }

    public void setTblroles(Tblroles tblroles) {
        this.tblroles = tblroles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblpermissionsPK != null ? tblpermissionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblpermissions)) {
            return false;
        }
        Tblpermissions other = (Tblpermissions) object;
        if ((this.tblpermissionsPK == null && other.tblpermissionsPK != null) || (this.tblpermissionsPK != null && !this.tblpermissionsPK.equals(other.tblpermissionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblpermissions[ tblpermissionsPK=" + tblpermissionsPK + " ]";
    }

}
