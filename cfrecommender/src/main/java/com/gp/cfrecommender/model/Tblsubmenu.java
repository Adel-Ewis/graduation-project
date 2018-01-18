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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohamed
 */
@Entity
@Table(name = "tblsubmenu", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblsubmenu.findAll", query = "SELECT t FROM Tblsubmenu t"),
    @NamedQuery(name = "Tblsubmenu.findBySubMenuID", query = "SELECT t FROM Tblsubmenu t WHERE t.subMenuID = :subMenuID"),
    @NamedQuery(name = "Tblsubmenu.findByAppearanceOrder", query = "SELECT t FROM Tblsubmenu t WHERE t.appearanceOrder = :appearanceOrder"),
    @NamedQuery(name = "Tblsubmenu.findByVisible", query = "SELECT t FROM Tblsubmenu t WHERE t.visible = :visible")})
public class Tblsubmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SubMenuID", nullable = false)
    private Integer subMenuID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AppearanceOrder", nullable = false)
    private int appearanceOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Visible", nullable = false)
    private int visible;
    @JoinColumn(name = "ParentMenuID", referencedColumnName = "MenuID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblmainmenu parentMenuID;
    @JoinColumn(name = "PageID", referencedColumnName = "PageID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tblpages pageID;

    public Tblsubmenu() {
    }

    public Tblsubmenu(Integer subMenuID) {
        this.subMenuID = subMenuID;
    }

    public Tblsubmenu(Integer subMenuID, int appearanceOrder, int visible) {
        this.subMenuID = subMenuID;
        this.appearanceOrder = appearanceOrder;
        this.visible = visible;
    }

    public Integer getSubMenuID() {
        return subMenuID;
    }

    public void setSubMenuID(Integer subMenuID) {
        this.subMenuID = subMenuID;
    }

    public int getAppearanceOrder() {
        return appearanceOrder;
    }

    public void setAppearanceOrder(int appearanceOrder) {
        this.appearanceOrder = appearanceOrder;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public Tblmainmenu getParentMenuID() {
        return parentMenuID;
    }

    public void setParentMenuID(Tblmainmenu parentMenuID) {
        this.parentMenuID = parentMenuID;
    }

    public Tblpages getPageID() {
        return pageID;
    }

    public void setPageID(Tblpages pageID) {
        this.pageID = pageID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subMenuID != null ? subMenuID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblsubmenu)) {
            return false;
        }
        Tblsubmenu other = (Tblsubmenu) object;
        if ((this.subMenuID == null && other.subMenuID != null) || (this.subMenuID != null && !this.subMenuID.equals(other.subMenuID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblsubmenu[ subMenuID=" + subMenuID + " ]";
    }

}
