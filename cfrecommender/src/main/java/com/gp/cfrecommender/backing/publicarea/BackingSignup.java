/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.publicarea;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblroles;
import com.gp.cfrecommender.facade.FacadeTblsystemusers;
import com.gp.cfrecommender.facade.FacadeTblusers;
import com.gp.cfrecommender.model.Tblsystemusers;
import com.gp.cfrecommender.model.Tblusers;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Mohamed-Ali
 */
@Named
@ViewScoped
public class BackingSignup extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblsystemusers facadeTblsystemusers;
    @EJB
    FacadeTblusers facadeTblusers;
    @EJB
    FacadeTblroles facadeTblroles;

    Tblsystemusers userToRegister;
    String confirmedPassword;
    String codeforcesHandle;

    @PostConstruct
    public void init() {
        clearMe();
    }

    private void clearMe() {
        userToRegister = new Tblsystemusers();
    }

    public String goToLogin() {
        return "login.xhtml?faces-redirect=true";
    }

    public String goToIndex() {
        return "index.xhtml?faces-redirect=true";
    }

    public void doSignup() {
        // Validate that Codeforces Handle is Exist
        Tblusers cfUser = facadeTblusers.findCFUserByHandle(codeforcesHandle);
        if (cfUser == null) {
            sendMessageInDialog("Error", "Incorrect Codeforces Handle", Severity.Error);
            return;
        }
        // Validate Username is Unique
        Tblsystemusers systemUser = facadeTblsystemusers.findUserByUserName(userToRegister.getUserName());
        if (systemUser != null) {
            sendMessageInDialog("Error", "This Username is Already Registered", Severity.Error);
            return;
        }
        // Link System User with Codeforces Handle
        userToRegister.setUserHandle(cfUser);
        // Set User to be Active -- Need to Do Dynamic Activation Here
        userToRegister.setIsActive(1);
        // Create Role for User -- Role 1 : Trainee
        userToRegister.setSystemUserRoleID(facadeTblroles.find(1));
        // Persist User in Database
        facadeTblsystemusers.create(userToRegister);
        clearMe();
        sendMessageInDialog("Information", "You have been Registerd Successfully", Severity.Info);
    }

    public Tblsystemusers getUserToRegister() {
        return userToRegister;
    }

    public void setUserToRegister(Tblsystemusers userToRegister) {
        this.userToRegister = userToRegister;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getCodeforcesHandle() {
        return codeforcesHandle;
    }

    public void setCodeforcesHandle(String codeforcesHandle) {
        this.codeforcesHandle = codeforcesHandle;
    }

}
