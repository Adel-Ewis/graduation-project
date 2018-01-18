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
@Table(name = "tblpages", catalog = "cforces", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblpages.findAll", query = "SELECT t FROM Tblpages t"),
    @NamedQuery(name = "Tblpages.findByPageID", query = "SELECT t FROM Tblpages t WHERE t.pageID = :pageID"),
    @NamedQuery(name = "Tblpages.findByIconImgeName", query = "SELECT t FROM Tblpages t WHERE t.iconImgeName = :iconImgeName"),
    @NamedQuery(name = "Tblpages.findByPageFileName", query = "SELECT t FROM Tblpages t WHERE t.pageFileName = :pageFileName"),
    @NamedQuery(name = "Tblpages.findByPageMenuName", query = "SELECT t FROM Tblpages t WHERE t.pageMenuName = :pageMenuName"),
    @NamedQuery(name = "Tblpages.findByPagePath", query = "SELECT t FROM Tblpages t WHERE t.pagePath = :pagePath")})
public class Tblpages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PageID", nullable = false)
    private Integer pageID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IconImgeName", nullable = false, length = 100)
    private String iconImgeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PageFileName", nullable = false, length = 100)
    private String pageFileName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PageMenuName", nullable = false, length = 100)
    private String pageMenuName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "PagePath", nullable = false, length = 500)
    private String pagePath;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageID", fetch = FetchType.LAZY)
    private List<Tblsubmenu> tblsubmenuList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblpages", fetch = FetchType.LAZY)
    private List<Tblpermissions> tblpermissionsList;
    @OneToMany(mappedBy = "pageID", fetch = FetchType.LAZY)
    private List<Tblmainmenu> tblmainmenuList;

    public Tblpages() {
    }

    public Tblpages(Integer pageID) {
        this.pageID = pageID;
    }

    public Tblpages(Integer pageID, String iconImgeName, String pageFileName, String pageMenuName, String pagePath) {
        this.pageID = pageID;
        this.iconImgeName = iconImgeName;
        this.pageFileName = pageFileName;
        this.pageMenuName = pageMenuName;
        this.pagePath = pagePath;
    }

    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public String getIconImgeName() {
        return iconImgeName;
    }

    public void setIconImgeName(String iconImgeName) {
        this.iconImgeName = iconImgeName;
    }

    public String getPageFileName() {
        return pageFileName;
    }

    public void setPageFileName(String pageFileName) {
        this.pageFileName = pageFileName;
    }

    public String getPageMenuName() {
        return pageMenuName;
    }

    public void setPageMenuName(String pageMenuName) {
        this.pageMenuName = pageMenuName;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    @XmlTransient
    @JsonIgnore
    public List<Tblsubmenu> getTblsubmenuList() {
        return tblsubmenuList;
    }

    public void setTblsubmenuList(List<Tblsubmenu> tblsubmenuList) {
        this.tblsubmenuList = tblsubmenuList;
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
    public List<Tblmainmenu> getTblmainmenuList() {
        return tblmainmenuList;
    }

    public void setTblmainmenuList(List<Tblmainmenu> tblmainmenuList) {
        this.tblmainmenuList = tblmainmenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pageID != null ? pageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblpages)) {
            return false;
        }
        Tblpages other = (Tblpages) object;
        if ((this.pageID == null && other.pageID != null) || (this.pageID != null && !this.pageID.equals(other.pageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gp.cfrecommender.model.Tblpages[ pageID=" + pageID + " ]";
    }

}
