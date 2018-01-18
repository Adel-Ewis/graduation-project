/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.publicarea;

import com.gp.cfrecommender.backing.BaseBackingBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Mohamed-Ali
 */
@Named
@ViewScoped
public class BackingIndex extends BaseBackingBean implements Serializable {
    
    @PostConstruct
    public void init() {
        
    }
    
    public void clearMe(){
        
    }
    
    public String goToLogin() {
        return "login.xhtml?faces-redirect=true";
    }
    
    public String goToSignUp() {
        return "signup.xhtml?faces-redirect=true";
    }
    
}
