/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.error;

import com.gp.cfrecommender.backing.BaseBackingBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ITS
 */
@Named
@ViewScoped
public class BackingNotFound extends BaseBackingBean implements Serializable {

    @PostConstruct
    public void init() {

    }

    public String backToDashboard() {
        return "/p/Dashboard.xhtml?faces-redirect=true";
    }

}
