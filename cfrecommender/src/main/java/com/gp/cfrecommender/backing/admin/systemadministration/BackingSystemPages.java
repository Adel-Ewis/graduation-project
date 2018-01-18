/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.systemadministration;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblpages;
import com.gp.cfrecommender.model.Tblpages;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author mohamed
 */
@Named
@ViewScoped
public class BackingSystemPages extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblpages facadeTblpages;

    Tblpages selectedPage;

    @PostConstruct
    public void init() {
        clearMe();
    }

    public void clearMe() {
        selectedPage = new Tblpages();
    }

    public void addPage() {
        if (selectedPage.getPageID() != null) {
            facadeTblpages.edit(selectedPage);
            clearMe();
            sendMessageInDialog("Information", "Page Edited Successfully", Severity.Info);
        } else {
            facadeTblpages.create(selectedPage);
            clearMe();
            sendMessageInDialog("Information", "New Page Added Successfully", Severity.Info);
        }
    }

    public List<Tblpages> findAllPages() {
        return facadeTblpages.findAll();
    }

    public Tblpages getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Tblpages selectedPage) {
        this.selectedPage = selectedPage;
    }

}
