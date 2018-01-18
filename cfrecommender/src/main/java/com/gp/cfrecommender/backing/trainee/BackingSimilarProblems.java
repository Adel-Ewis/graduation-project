/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.trainee;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.facade.FacadeTblproblems;
import com.gp.cfrecommender.model.Tblcontests;
import com.gp.cfrecommender.model.Tblproblems;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

/**
 *
 * @author mohamed
 */
@Named
@ViewScoped
public class BackingSimilarProblems extends BaseBackingBean implements Serializable {

    Long problemID;
    Tblproblems selectedProblem;
    @EJB
    FacadeTblproblems facadeTblproblems;
    List<Tblproblems> similarProblems;

    @PostConstruct
    public void init() {
        clearMe();
        String problem = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("problemID");
        if (problem != null) {
            problemID = Long.parseLong(problem);
            selectedProblem = facadeTblproblems.find(problemID);
            prepareSimilarProblems();
        }
    }

    public void clearMe() {
        selectedProblem = new Tblproblems();
        similarProblems = new ArrayList<>();
    }

    private void prepareSimilarProblems() {
        try {
            // Get Data Model From Application Context
            DataModel dm = getDataModelFromContext();

            // tyeb fe kaza no3 mn similarity w homa
            // UncenteredCosineSimilarity , TanimotoCoefficientSimilarity , LogLikelihoodSimilarity , EuclideanDistanceSimilarity , CityBlockSimilarity
            // ana msta5dem delwa2ty TanimotoCoefficientSimilarity ely 3awz yegarab no3 tany ya5od esm copy mn fo2 w y7oto mkan TanimotoCoefficientSimilarity
            ItemSimilarity sim = new TanimotoCoefficientSimilarity(dm);
            List<RecommendedItem> similarProblemss = new ArrayList<>();
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
            Tblcontests tblcontest = selectedProblem.getContestID();
            for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {

                long itemID = items.nextLong();
                if (itemID == problemID) {
                    // hena bn7aded 3dad el recommendation ely 3awzenha
                    similarProblemss = recommender.mostSimilarItems(itemID, 15);
                }
            }
            for (RecommendedItem similarProblem : similarProblemss) {
                Tblproblems find = facadeTblproblems.find(similarProblem.getItemID());
                if (find.getContestID().getContestCFID() != tblcontest.getContestCFID() && dm.getPreferenceValue(userSession.getCurrentUser().getUserHandle().getUserID(), find.getProblemID()) == null) {
                    similarProblems.add(find);
                }
            }
        } catch (TasteException ex) {
            System.out.println("Taste exception");
        }
    }

    public Long getProblemID() {
        return problemID;
    }

    public void setProblemID(Long problemID) {
        this.problemID = problemID;
    }

    public Tblproblems getSelectedProblem() {
        return selectedProblem;
    }

    public void setSelectedProblem(Tblproblems selectedProblem) {
        this.selectedProblem = selectedProblem;
    }

    public List<Tblproblems> getSimilarProblems() {
        return similarProblems;
    }

    public void setSimilarProblems(List<Tblproblems> similarProblems) {
        this.similarProblems = similarProblems;
    }

}
