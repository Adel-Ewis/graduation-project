/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.utils.recommendation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gp.cfrecommender.backing.BackingDashBoard;
import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblproblems;
import com.gp.cfrecommender.model.Tblproblems;
import com.gp.cfrecommender.utils.recommendation.refresherhelpers.Result;
import com.gp.cfrecommender.utils.recommendation.refresherhelpers.results;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

/**
 *
 * @author mohamed
 */
@Named
@ApplicationScoped
public class DataRefresher extends BaseBackingBean implements Serializable {

    final String path = "/home/mostafa_thabet/NetBeansProjects/graduationproject/matrices/";

    @EJB
    FacadeTblproblems facadeTblproblems;

    public void refreshUserData(String userHandle, long userID) {
        if (!downloadUserSubmissions(userHandle)) {
            sendMessageInDialog("Error", "Couldn't Download Submissions, Try Again Later", Severity.Error);
            return;
        }
        try {
            parseUserSubmissions(userHandle, userID);
            updateRecommendations();
            userSession.prepareRecommendations();
            sendMessageInDialog("Information", "Your Data has been Updated", Severity.Info);
        } catch (Exception e) {
            sendMessageInDialog("Error", "Some Error Occured, Try Again Later", Severity.Error);
        }

    }

    private boolean downloadUserSubmissions(String UserHandle) {
        // Connect to API
        URL url = null;
        try {
            url = new URL("http://codeforces.com/api/user.status?handle=" + UserHandle + "&from=1");
        } catch (MalformedURLException ex) {
            sendMessageInDialog("Error", "Error Creating URL, Try Again Later", Severity.Error);
            Logger.getLogger(BackingDashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException ex) {
            sendMessageInDialog("Error", "Couldn't Connect to Codeforces Now, Try Again Later", Severity.Error);
            Logger.getLogger(BackingDashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream in = null;
        try {
            in = con.getInputStream();
        } catch (IOException ex) {
            sendMessageInDialog("Error", "Error Reading Codeforces Response", Severity.Error);
            Logger.getLogger(BackingDashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Store Response in Text File
            String response = read(in);
            if (!response.equals("")) {
                FileUtils.writeStringToFile(new File(path + UserHandle + ".txt"), response, "UTF-8");
            } else {
                return false;
            }
            System.out.println("Submission for " + UserHandle + " Fetched and Stored Succesfully");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(BackingDashBoard.class.getName()).log(Level.SEVERE, null, ex);
            sendMessageInDialog("Error", "Couldn't Save Submissions, Try Again Later", Severity.Error);
            return false;
        }
    }

    private void parseUserSubmissions(String userHandle, long userID) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Long> userSolvedProblems = new ArrayList<Long>();
        results res = null;
        // parsing Json files
        try {
            res = mapper.readValue(new File(path + userHandle + ".txt"), results.class);
        } catch (IOException ex) {
            sendMessageInDialog("Error", "Couldn't Open Submissions File After Downloadin", Severity.Error);
            Logger.getLogger(DataRefresher.class.getName()).log(Level.SEVERE, null, ex);
        }
        //put results from jason to arraylist
        for (int i = 0; i < res.getResult().length; i++) {
            Result currentResult = res.getResult()[i];
            Tblproblems problem = facadeTblproblems.findProblemByContestIdAndIndex(currentResult.getProblem().getIndex(), Integer.valueOf(String.valueOf(currentResult.getProblem().getContestId())));
            if (currentResult.getVerdict().equals("OK")) { //to garuntee that the problem is exists in database
                if (problem != null) {
                    userSolvedProblems.add(problem.getProblemID());
                }
            }
        } //end of loop
        //Write new user matrix
        StringBuilder input = new StringBuilder("");
        for (int i = 0; i < userSolvedProblems.size(); i++) {
            input.append(userID).append(",").append(userSolvedProblems.get(i).toString()).append(",1");
            if (i != (userSolvedProblems.size() - 1)) {
                input.append("\n");
            }
        }
        try {
            FileUtils.writeStringToFile(new File(path + "mahout-format-binary." + userID + ".csv"), input.toString(), "UTF-8");

        } catch (IOException e) {
            sendMessageInDialog("Error", "Couldn't Update Submissions, Try Again Later", Severity.Error);
        } // end of parsing and file creation

    }

    private void updateRecommendations() {
        // Get Data Model and Matrix Data From Application Context
        DataModel dataModel = getDataModelFromContext();
        HashMap<Integer, HashSet<Integer>> matrixData = getMatrixDataFromContext();
// Refresh Data Model
        dataModel.refresh(null);
// Refres matrixData
        ArrayList<Integer> users = new ArrayList<>();
        try {
            users = getUSersIDs(getDataModelFromContext());
        } catch (TasteException ex) {
            Logger.getLogger(DataRefresher.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int user : users) {
            HashSet<Integer> put = new HashSet<>();
            try {
                put = new HashSet<>(getUserPref(user, getDataModelFromContext()));
            } catch (TasteException ex) {
                Logger.getLogger(DataRefresher.class.getName()).log(Level.SEVERE, null, ex);
            }
            matrixData.put(user, put);
        }
        // Add Data Model to Application Context
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().replace("dataModel", dataModel);
        // Add Matrix Data to Application Context
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().replace("matrixData", matrixData);

    }

    private ArrayList<Integer> getUSersIDs(DataModel dm) throws TasteException {
        ArrayList<Integer> users = new ArrayList<>();
        for (LongPrimitiveIterator it = dm.getUserIDs(); it.hasNext();) {
            users.add(Integer.parseInt(Long.toString(it.nextLong())));
        }
        return users;
    }

    private HashSet<Integer> getUserPref(int userID, DataModel dm) throws TasteException {
        HashSet<Integer> res = new HashSet<>();
        PreferenceArray items = dm.getPreferencesFromUser(userID);
        for (Preference item : items) {
//                System.out.println(item.getItemID());
            res.add(Integer.parseInt(Long.toString(item.getItemID())));
        }
//        System.out.println(res);
        return res;
    }

}
