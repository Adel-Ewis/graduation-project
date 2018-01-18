/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing;

import com.gp.cfrecommender.model.Tblproblems;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mohamed
 */
@Named
@ViewScoped
public class BackingDashBoard extends BaseBackingBean implements Serializable {

    @PostConstruct
    public void init() {

    }

}
