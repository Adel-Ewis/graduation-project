/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.systemadministration;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblmainmenu;
import com.gp.cfrecommender.facade.FacadeTblpages;
import com.gp.cfrecommender.facade.FacadeTblsubmenu;
import com.gp.cfrecommender.model.Tblmainmenu;
import com.gp.cfrecommender.model.Tblpages;
import com.gp.cfrecommender.model.Tblsubmenu;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mohamed
 */
@Named
@ViewScoped
public class BackingSystemMenus extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblmainmenu facadeTblmainmenu;
    @EJB
    FacadeTblpages facadeTblpages;
    @EJB
    FacadeTblsubmenu facadeTblsubmenu;

    Tblmainmenu selectedMainMenu;
    Tblpages selectedPage;
    Tblpages subMenu;

    @PostConstruct
    public void init() {
        clearMe();
    }

    public void clearMe() {
        selectedMainMenu = new Tblmainmenu();
        selectedPage = new Tblpages();
    }

    public List<Tblmainmenu> findAllMainMenus() {
        return facadeTblmainmenu.findAll();
    }

    public List<Tblpages> findAllPages() {
        return facadeTblpages.findAll();
    }

    public List<Tblpages> findPagesNotInMenu(Tblmainmenu mainMenu) {
        return facadeTblpages.findAllPagesNotInMenu(mainMenu);
    }

    public void addMainMenu() {
        if (selectedMainMenu.getAppearanceOrder() == 0) {
            sendMessageInDialog("Error", "Please Enter Correct Appearance Order", Severity.Info);
            return;
        }
        if (selectedPage.getPageID() == null) {
            selectedMainMenu.setPageID(null);
        } else {
            selectedMainMenu.setPageID(selectedPage);
        }
        if (selectedMainMenu.getMenuID() == null) {
            facadeTblmainmenu.create(selectedMainMenu);
            clearMe();
            sendMessageInDialog("Information", "Main Menu Added Successfully", Severity.Info);
        } else {
            facadeTblmainmenu.edit(selectedMainMenu);
            clearMe();
            sendMessageInDialog("Information", "Main Menu Edited Successfully", Severity.Info);
        }

    }

    public void addSubMenu(Tblpages page) {
        Tblsubmenu tblsubmenu = new Tblsubmenu();
        tblsubmenu.setParentMenuID(selectedMainMenu);
        tblsubmenu.setPageID(page);
        tblsubmenu.setVisible(1);
        tblsubmenu.setAppearanceOrder(selectedMainMenu.getTblsubmenuList().size() + 1);
        facadeTblsubmenu.create(tblsubmenu);
        selectedMainMenu = facadeTblmainmenu.find(selectedMainMenu.getMenuID());
        sendMessageInDialog("Information", "Sub-Menu Added Successfully", Severity.Info);
    }

    public void changeVisibility(Tblsubmenu subMenu) {
        if (subMenu.getVisible() == 1) {
            subMenu.setVisible(0);
        } else {
            subMenu.setVisible(1);
        }
        facadeTblsubmenu.edit(subMenu);
        sendMessageInDialog("Information", "Sub-Menu Updated Successfully", Severity.Info);
    }

    public void deleteSubMenu(Tblsubmenu subMenu) {
        if (facadeTblsubmenu.deleteSubMenu(subMenu) > 0) {
            selectedMainMenu = facadeTblmainmenu.find(selectedMainMenu.getMenuID());
            sendMessageInDialog("Information", "Sub-Menu Deleted Successfully", Severity.Info);
            return;
        }
        sendMessageInDialog("Error", "Can't Delete Right Now, Try Again Later", Severity.Error);
    }

    public void deleteMainMenu(Tblmainmenu mainMenu) {
        if (!mainMenu.getTblsubmenuList().isEmpty()) {
            sendMessageInDialog("Error", "Can't Delete Main Menu, Delete All Sub-Menus First", Severity.Error);
        } else {
            if (facadeTblmainmenu.delete(mainMenu) > 0) {
                sendMessageInDialog("Informtion", "Main Menu Deleted Successfully", Severity.Info);
                return;
            }
            sendMessageInDialog("Error", "Can't Delete Right Now, Try Again Later", Severity.Error);
        }
    }

    public Tblmainmenu getSelectedMainMenu() {
        return selectedMainMenu;
    }

    public void setSelectedMainMenu(Tblmainmenu selectedMainMenu) {
        this.selectedMainMenu = selectedMainMenu;
    }

    public Tblpages getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Tblpages selectedPage) {
        this.selectedPage = selectedPage;
    }

}
