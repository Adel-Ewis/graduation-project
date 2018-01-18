/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.systemadministration;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblroles;
import com.gp.cfrecommender.facade.FacadeTblsystemusers;
import com.gp.cfrecommender.facade.FacadeTblusers;
import com.gp.cfrecommender.model.Tblroles;
import com.gp.cfrecommender.model.Tblsystemusers;
import com.gp.cfrecommender.model.Tblusers;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ITS
 */
@Named
@ViewScoped
public class BackingSystemUsers extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblsystemusers facadeTblsystemusers;
    @EJB
    FacadeTblroles facadeTblroles;
    @EJB
    FacadeTblusers facadeTblusers;
    Tblsystemusers selectedUser;
    Tblroles selectedRole;
    String userHandle;
    String resettedPassword;

    @PostConstruct
    public void init() {
        clearMe();
    }

    public void clearMe() {
        // Create Selected User
        selectedUser = new Tblsystemusers();
        selectedUser.setUserHandle(new Tblusers());
        // Create Selected Role
        selectedRole = new Tblroles();
    }

    public void addUser() {
        if (selectedUser.getSystemUserID() == null) {
            System.out.println("Add User Called");
            // We Need to Check If User Name Already Token
            if (facadeTblsystemusers.findUserByUserName(selectedUser.getUserName()) != null) {
                selectedUser.setPassword("");
                selectedUser.setUserName("");
                sendMessageInDialog("Error", "Username Already Exists", Severity.Error);
                return;
            }
            if (selectedRole.getRoleID() == 2) {
                // Handle Add Admin
                // Set User Handle to Null
                selectedUser.setUserHandle(null);
                // Assign Selected Role
                selectedUser.setSystemUserRoleID(selectedRole);
            } else {
                // Check if User Handle Not Found in Codeforces Data
                Tblusers findCFUserByHandle = facadeTblusers.findCFUserByHandle(userHandle);
                if (findCFUserByHandle == null) {
                    sendMessageInDialog("Error", "Wronge User Handle", Severity.Error);
                    return;
                }
            }
            // Persist User in Database
            facadeTblsystemusers.create(selectedUser);
            clearMe();
            sendMessageInDialog("Information", "User Added Successfully", Severity.Info);
        } else {
            facadeTblsystemusers.edit(selectedUser);
            clearMe();
            sendMessageInDialog("Information", "User Edited Successfully", Severity.Info);
        }
    }

    public void editUser() {
        System.out.println("Edit Called for User" + selectedUser.getUserName());
    }

    public void deleteUser() {
        // Delete User
        facadeTblsystemusers.deleteSystemUser(selectedUser.getSystemUserID());
        clearMe();
        sendMessageInDialog("Information", "User Deleted Successfully", Severity.Info);
    }

    public void changeUserStatus() {
        if (selectedUser.getIsActive() == 1) {
            selectedUser.setIsActive(0);
        } else {
            selectedUser.setIsActive(1);
        }
        facadeTblsystemusers.edit(selectedUser);
        if (selectedUser.getIsActive() == 1) {
            sendMessageInDialog("Information", "User With Username " + selectedUser.getUserName() + " is Activated", Severity.Info);
        } else {
            sendMessageInDialog("Information", "User With Username " + selectedUser.getUserName() + " is Deactivated", Severity.Info);
        }
        clearMe();
    }

    public void resetPassword() {
        selectedUser.setPassword(resettedPassword);
        facadeTblsystemusers.edit(selectedUser);
        sendMessageInDialog("Information", "Password Resseted for User With Username " + selectedUser.getUserName(), Severity.Info);
        clearMe();
    }

    public List<Tblsystemusers> findAllUsers() {
        return facadeTblsystemusers.findAll();
    }

    public List<Tblroles> findAllRoles() {
        return facadeTblroles.findAll();
    }

    public Tblsystemusers getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Tblsystemusers selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Tblroles getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Tblroles selectedRole) {
        this.selectedRole = selectedRole;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getResettedPassword() {
        return resettedPassword;
    }

    public void setResettedPassword(String resettedPassword) {
        this.resettedPassword = resettedPassword;
    }

}
