/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.publicarea;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblsystemusers;
import com.gp.cfrecommender.model.Tblsystemusers;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.mahout.cf.taste.common.TasteException;

/**
 *
 * @author ITS
 */
@Named
@ViewScoped
public class BackingLogin extends BaseBackingBean implements Serializable {

    String userName;
    String password;
    @EJB
    FacadeTblsystemusers facadeTblsystemusers;

    @PostConstruct
    public void init() {
        clearMe();
    }

    private void clearMe() {

    }

    public String goToSignUp() {
        return "signup.xhtml?faces-redirect=true";
    }

    public String doLogin() throws TasteException {
        System.out.println("User Name :" + userName);
        System.out.println("Password :" + password);
        Tblsystemusers retrievedUser = facadeTblsystemusers.checkIfUserExist(userName, password);
        if (retrievedUser == null) {
            System.out.println("User Not Found");
            sendMessageInDialog("Error", "Incorrect Username or Password", Severity.Error);
            return "";
        }
        if (retrievedUser.getIsActive() != 1) {
            System.out.println("User is Not Active");
            sendMessageInDialog("Error", "This User is Not Active", Severity.Error);
            return "";
        }
        System.out.println("User Exist and Active");
        userSession.setCurrentUser(retrievedUser.getSystemUserID());
        return "/p/Dashboard.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
