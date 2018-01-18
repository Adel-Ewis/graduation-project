/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mohamed
 */
@Embeddable
public class TblpermissionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PageID", nullable = false)
    private int pageID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RoleID", nullable = false)
    private int roleID;

    public TblpermissionsPK() {
    }

    public TblpermissionsPK(int pageID, int roleID) {
        this.pageID = pageID;
        this.roleID = roleID;
    }

    public int getPageID() {
        return pageID;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pageID;
        hash += (int) roleID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblpermissionsPK)) {
            return false;
        }
        TblpermissionsPK other = (TblpermissionsPK) object;
        if (this.pageID != other.pageID) {
            return false;
        }
        if (this.roleID != other.roleID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.TblpermissionsPK[ pageID=" + pageID + ", roleID=" + roleID + " ]";
    }

}
