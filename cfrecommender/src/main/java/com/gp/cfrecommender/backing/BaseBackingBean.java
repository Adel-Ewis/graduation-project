/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing;

import com.gp.cfrecommender.session.UserSession;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblpages;
import com.gp.cfrecommender.model.Tblmainmenu;
import com.gp.cfrecommender.model.Tblpages;
import com.gp.cfrecommender.model.Tblsubmenu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.apache.mahout.cf.taste.model.DataModel;

/**
 *
 * @author Mohamed Ali
 */
public class BaseBackingBean {

    @Inject
    protected UserSession userSession;
    @EJB
    FacadeTblpages facadeTblpages;

    public String findIconName() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        //System.out.println(viewId);
        String pattern = "(.*)/(.*).xhtml";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(viewId);
        m.find();
        String pageName = m.group(2);
        Tblpages page = facadeTblpages.findPageByName(pageName);
        return page.getIconImgeName();
    }

    public String findPageName() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        //System.out.println(viewId);
        String pattern = "(.*)/(.*).xhtml";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(viewId);
        m.find();
        String pageName = m.group(2);
        Tblpages page = facadeTblpages.findPageByName(pageName);
        return page.getPageMenuName();
    }

    public String findMainMenueName() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        //System.out.println(viewId);
        String pattern = "(.*)/(.*).xhtml";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(viewId);
        m.find();
        String pageName = m.group(2);
        Tblpages page = facadeTblpages.findPageByName(pageName);
        List<Tblsubmenu> tblsubmenuList = page.getTblsubmenuList();
        if (tblsubmenuList.isEmpty()) {
            return "";
        }
        Tblmainmenu tblmainmenu = tblsubmenuList.get(0).getParentMenuID();
        return tblmainmenu.getMenuName();
    }

    public void sendMessage(String msgHead, String msgDetails, Severity severity) {
        FacesMessage fm = new FacesMessage(severity.getValue(), msgHead, msgDetails);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    protected boolean doesParameterExist(String paramName) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey(paramName);
    }

    protected DataModel getDataModelFromContext() {
        return (DataModel) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("dataModel");
    }

    protected HashMap<Integer, HashSet<Integer>> getMatrixDataFromContext() {
        return (HashMap<Integer, HashSet<Integer>>) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("matrixData");
    }

    public void sendMessageInDialog(String msgHead, String msgDetails, Severity severity) {
        FacesMessage fm = new FacesMessage(severity.getValue(), msgHead, msgDetails);
        //FacesContext.getCurrentInstance().addMessage(null, fm);
        RequestContext currentInstance = RequestContext.getCurrentInstance();
        currentInstance.showMessageInDialog(fm);
    }

    protected String read(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            sendMessageInDialog("Error", "Some Error Occured, Try Again Later", Severity.Error);
            return "";
        }
    }

}
