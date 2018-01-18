/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.systemadministration;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblpages;
import com.gp.cfrecommender.facade.FacadeTblpermissions;
import com.gp.cfrecommender.facade.FacadeTblroles;
import com.gp.cfrecommender.model.Tblpages;
import com.gp.cfrecommender.model.Tblpermissions;
import com.gp.cfrecommender.model.TblpermissionsPK;
import com.gp.cfrecommender.model.Tblroles;
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
public class BackingPermissions extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblpermissions facadeTblpermissions;
    @EJB
    FacadeTblpages facadeTblpages;
    @EJB
    FacadeTblroles facadeTblroles;

    Tblpages selectedPage;
    Tblroles selectedRole;

    @PostConstruct
    public void init() {
        clearMe();
    }

    public void clearMe() {
        selectedPage = new Tblpages();
        selectedRole = new Tblroles();
    }

    public List<Tblpermissions> findAllPermissions() {
        return facadeTblpermissions.findAll();
    }

    public List<Tblpages> findAllPages() {
        return facadeTblpages.findAll();
    }

    public List<Tblroles> findAllRoles() {
        return facadeTblroles.findAll();
    }

    public void addPermission() {
        boolean checkIfPageHavePermission = facadeTblpermissions.checkIfPageHavePermission(selectedPage, selectedRole);
        if (checkIfPageHavePermission) {
            sendMessageInDialog("Error", "This Role Already Have Permission to this Page", Severity.Error);
            clearMe();
            return;
        }
        Tblpermissions permission = new Tblpermissions();
        permission.setTblpermissionsPK(new TblpermissionsPK(selectedPage.getPageID(), selectedRole.getRoleID()));
        permission.setTblroles(selectedRole);
        permission.setTblpages(selectedPage);
        permission.setViewPermission(1);
        facadeTblpermissions.create(permission);
        clearMe();
        sendMessageInDialog("Information", "Permission Added Successfully", Severity.Info);
    }

    public void changePermission(Tblpermissions permission) {
        if (permission.getViewPermission() == 1) {
            permission.setViewPermission(0);
        } else {
            permission.setViewPermission(1);
        }
        facadeTblpermissions.edit(permission);
        sendMessageInDialog("Information", "Permission Updated Successfully", Severity.Info);

    }

    public String findRoleNameByPermission(Tblpermissions permission) {
        return facadeTblroles.find(permission.getTblpermissionsPK().getRoleID()).getRoleName();
    }

    public String findPageNameByPermission(Tblpermissions permission) {
        return facadeTblpages.find(permission.getTblpermissionsPK().getPageID()).getPageMenuName();
    }

    public Tblpages getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Tblpages selectedPage) {
        this.selectedPage = selectedPage;
    }

    public Tblroles getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Tblroles selectedRole) {
        this.selectedRole = selectedRole;
    }

}
