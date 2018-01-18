/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.template;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.facade.FacadeTblmainmenu;
import com.gp.cfrecommender.facade.FacadeTblsubmenu;
import com.gp.cfrecommender.model.Tblmainmenu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author ITS
 */
@Named
@RequestScoped
public class BackingLeftMenu extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblmainmenu facadeTblmainmenu;
    @EJB
    FacadeTblsubmenu facadeTblsubmenu;
    List<Tblmainmenu> menuModel;

    @PostConstruct
    public void init() {
        menuModel = facadeTblmainmenu.findAll();
        if (menuModel == null) {
            menuModel = new ArrayList<>();
        }
    }

    public boolean checkIfPageSelected(String currentPage, String pageName) {
        return currentPage.equals(pageName);
    }

    public boolean checkIfMenuSelected(String currentMenu, String menuName) {
        return currentMenu.equals(menuName);
    }

    public String generateURL(String pagePath, String pageFileName) {
        return pagePath + pageFileName + ".xhtml" + "?faces-redirect=true";
    }

    public List<Tblmainmenu> getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(List<Tblmainmenu> menuModel) {
        this.menuModel = menuModel;
    }

}
