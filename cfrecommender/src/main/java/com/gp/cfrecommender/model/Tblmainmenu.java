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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "tblmainmenu", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblmainmenu.findAll", query = "SELECT t FROM Tblmainmenu t"),
    @NamedQuery(name = "Tblmainmenu.findByMenuID", query = "SELECT t FROM Tblmainmenu t WHERE t.menuID = :menuID"),
    @NamedQuery(name = "Tblmainmenu.findByAppearanceOrder", query = "SELECT t FROM Tblmainmenu t WHERE t.appearanceOrder = :appearanceOrder"),
    @NamedQuery(name = "Tblmainmenu.findByMenuName", query = "SELECT t FROM Tblmainmenu t WHERE t.menuName = :menuName"),
    @NamedQuery(name = "Tblmainmenu.findByIconImageName", query = "SELECT t FROM Tblmainmenu t WHERE t.iconImageName = :iconImageName")})
public class Tblmainmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MenuID", nullable = false)
    private Integer menuID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AppearanceOrder", nullable = false)
    private int appearanceOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MenuName", nullable = false, length = 100)
    private String menuName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IconImageName", nullable = false, length = 100)
    private String iconImageName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentMenuID", fetch = FetchType.LAZY)
    private List<Tblsubmenu> tblsubmenuList;
    @JoinColumn(name = "PageID", referencedColumnName = "PageID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tblpages pageID;

    public Tblmainmenu() {
    }

    public Tblmainmenu(Integer menuID) {
        this.menuID = menuID;
    }

    public Tblmainmenu(Integer menuID, int appearanceOrder, String menuName, String iconImageName) {
        this.menuID = menuID;
        this.appearanceOrder = appearanceOrder;
        this.menuName = menuName;
        this.iconImageName = iconImageName;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public int getAppearanceOrder() {
        return appearanceOrder;
    }

    public void setAppearanceOrder(int appearanceOrder) {
        this.appearanceOrder = appearanceOrder;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIconImageName() {
        return iconImageName;
    }

    public void setIconImageName(String iconImageName) {
        this.iconImageName = iconImageName;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsubmenu> getTblsubmenuList() {
        return tblsubmenuList;
    }

    public void setTblsubmenuList(List<Tblsubmenu> tblsubmenuList) {
        this.tblsubmenuList = tblsubmenuList;
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
        hash += (menuID != null ? menuID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblmainmenu)) {
            return false;
        }
        Tblmainmenu other = (Tblmainmenu) object;
        if ((this.menuID == null && other.menuID != null) || (this.menuID != null && !this.menuID.equals(other.menuID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblmainmenu[ menuID=" + menuID + " ]";
    }

}
