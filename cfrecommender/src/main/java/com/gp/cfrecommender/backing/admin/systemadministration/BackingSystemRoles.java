/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.systemadministration;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblroles;
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
public class BackingSystemRoles extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblroles facadeTblroles;
    Tblroles selectedRole;

    @PostConstruct
    public void init() {
        clearMe();
    }

    public void clearMe() {
        selectedRole = new Tblroles();
    }

    public List<Tblroles> findAllRoles() {
        return facadeTblroles.findAll();
    }

    public void addRole() {
        if (selectedRole.getRoleID() == null) {
            facadeTblroles.create(selectedRole);
            clearMe();
            sendMessageInDialog("Information", "Role Added Successfully", Severity.Info);
        } else {
            facadeTblroles.edit(selectedRole);
            clearMe();
            sendMessageInDialog("Information", "Role Edited Successfully", Severity.Info);
        }
    }

    public Tblroles getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Tblroles selectedRole) {
        this.selectedRole = selectedRole;
    }

}
