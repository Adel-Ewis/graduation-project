/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.session;

import com.gp.cfrecommender.facade.FacadeTblmainmenu;
import com.gp.cfrecommender.facade.FacadeTblpermissions;
import com.gp.cfrecommender.facade.FacadeTblproblems;
import com.gp.cfrecommender.facade.FacadeTblsystemusers;
import com.gp.cfrecommender.model.Tblmainmenu;
import com.gp.cfrecommender.model.Tblpermissions;
import com.gp.cfrecommender.model.Tblproblems;
import com.gp.cfrecommender.model.Tblroles;
import com.gp.cfrecommender.model.Tblsubmenu;
import com.gp.cfrecommender.model.Tblsystemusers;
import com.gp.cfrecommender.utils.recommendation.RecommenderEngine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

/**
 *
 * @author Mohamed-Ali
 */
@Named
@SessionScoped
public class UserSession implements Serializable {

    private Long userID;
    private List<Tblproblems> recommendedProblems;
    @EJB
    FacadeTblsystemusers facadeTblsystemusers;
    @EJB
    FacadeTblpermissions facadeTblpermissions;
    @EJB
    FacadeTblmainmenu facadeTblmainmenu;
    @EJB
    FacadeTblproblems facadeTblproblems;
    ArrayList<Integer> users = new ArrayList<>();

    public String logOut() {
        this.userID = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public char findCurrentUserRole() {
        Tblsystemusers currentUser = getCurrentUser();
        if (currentUser.getSystemUserRoleID().getRoleID() == 2) {
            return 'A';
        } else {
            return 'T';
        }
    }

    public int checkPermissionForPageByPageName(String pageName) {
        if (getCurrentUser() == null) {
            return 0;
        }
        Tblroles systemUserRoleID = getCurrentUser().getSystemUserRoleID();
        List<Tblpermissions> allPermissions = facadeTblpermissions.findAll();
        for (Tblpermissions permission : allPermissions) {
            if (permission.getTblpages().getPageFileName().equals(pageName) && permission.getTblroles().getRoleName().equals(systemUserRoleID.getRoleName())) {
                if (permission.getViewPermission() == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int doesUserHavePermissionOnMenu(int menuID) {
        if (getCurrentUser() == null) {
            return 0;
        }
        Tblmainmenu menu = facadeTblmainmenu.find(menuID);
        List<Tblsubmenu> allSubMenus = menu.getTblsubmenuList();
        int counter = 0;
        for (Tblsubmenu subMenu : allSubMenus) {
            counter += checkPermissionForPageByPageName(subMenu.getPageID().getPageFileName());
        }
        return counter == 0 ? 0 : 1;
    }

    public void prepareRecommendations() throws TasteException {
        // Get Current User ID in Database
        Tblsystemusers currentUser = facadeTblsystemusers.find(userID);
        Long logedUserID = currentUser.getUserHandle().getUserID();
        // Get Number of Solved Problems By User "New User"
        if (getDataModelFromContext().getPreferencesFromUser(logedUserID).length() == 0) {
            List<Tblproblems> topSolved = facadeTblproblems.findAllProblemsSortedBySolvedCount();
            recommendedProblems = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                recommendedProblems.add(topSolved.get(i));
            }
            long seed = System.nanoTime();
            Collections.shuffle(recommendedProblems, new Random(seed));
            for(int i = 15; i < 50; i++) {
                recommendedProblems.remove(i);
            }
        } else {
            RecommenderEngine RE = new RecommenderEngine();
            Map<Double, Integer> problems;
            problems = RE.Get_Recommendation(getMatrixData(), Integer.valueOf(logedUserID.toString()));
            System.out.println(problems);
            System.out.println(problems.size());

            recommendedProblems = new ArrayList<>();
            for (Map.Entry<Double, Integer> entry : problems.entrySet()) {
                Integer value = entry.getValue();
                recommendedProblems.add(facadeTblproblems.find(Long.valueOf(value.toString())));
            }
        }
    }

    public Tblsystemusers getCurrentUser() {
        if (userID == null || userID == 0) {
            return null;
        }
        Tblsystemusers currentUser = facadeTblsystemusers.find(userID);
        return currentUser;
    }

    public void setCurrentUser(Long newUserId) throws TasteException {
        this.userID = newUserId;
        // Call Prepare Recommende Problems For User In Case of Trainee
        Tblsystemusers currentUser = facadeTblsystemusers.find(userID);
        if (currentUser.getSystemUserRoleID().getRoleName().equals("Trainee")) {
            prepareRecommendations();
        }
    }

    public List<Tblproblems> getRecommendedProblems() {
        return recommendedProblems;
    }

    public void setRecommendedProblems(List<Tblproblems> recommendedProblems) {
        this.recommendedProblems = recommendedProblems;
    }

    private HashMap<Integer, HashSet<Integer>> getMatrixData() {
        return (HashMap<Integer, HashSet<Integer>>) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("matrixData");
    }

    private DataModel getDataModelFromContext() {
        return (DataModel) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("dataModel");
    }

}
