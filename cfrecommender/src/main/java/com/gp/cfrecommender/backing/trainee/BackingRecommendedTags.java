/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.trainee;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.facade.FacadeTblproblemtags;
import com.gp.cfrecommender.facade.FacadeTblproblemtagsbridge;
import com.gp.cfrecommender.model.Tblproblems;
import com.gp.cfrecommender.model.Tblproblemtags;
import com.gp.cfrecommender.model.Tblproblemtagsbridge;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class BackingRecommendedTags extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblproblemtags facadeTblproblemtags;
    @EJB
    FacadeTblproblemtagsbridge facadeTblproblemtagsbridge;
    List<Tblproblems> recProblems;
    Map<Tblproblemtags, List<Tblproblems>> userProblems;

    @PostConstruct
    public void init() {
        recProblems = userSession.getRecommendedProblems();
        userProblems = new HashMap<>();
        for (Tblproblems recProblem : recProblems) {
            List<Tblproblemtagsbridge> tblproblemtagsbridgeList = recProblem.getTblproblemtagsbridgeList();
            for (Tblproblemtagsbridge tblproblemtagsbridge : tblproblemtagsbridgeList) {
                List<Tblproblems> get = userProblems.get(tblproblemtagsbridge.getTagID());
                if (get == null) {
                    List<Tblproblems> temp = new ArrayList<>();
                    temp.add(recProblem);
                    userProblems.put(tblproblemtagsbridge.getTagID(), temp);
                } else {
                    get.add(recProblem);
                    userProblems.put(tblproblemtagsbridge.getTagID(), get);
                }
            }
        }
    }

    public String findTagName(Tblproblemtags tag) {
        return tag.getTagName();
    }

    public Map<Tblproblemtags, List<Tblproblems>> getUserProblems() {
        return userProblems;
    }

    public void setUserProblems(Map<Tblproblemtags, List<Tblproblems>> userProblems) {
        this.userProblems = userProblems;
    }

}
