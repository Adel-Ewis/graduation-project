/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.codeforcesdata;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblcontestphases;
import com.gp.cfrecommender.facade.FacadeTblcontests;
import com.gp.cfrecommender.facade.FacadeTblcontesttypes;
import com.gp.cfrecommender.model.Tblcontestphases;
import com.gp.cfrecommender.model.Tblcontests;
import com.gp.cfrecommender.model.Tblcontesttypes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mohamed
 */
@Named
@ViewScoped
public class BackingContests extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblcontests facadeTblcontests;
    @EJB
    FacadeTblcontesttypes facadeTblcontesttypes;
    @EJB
    FacadeTblcontestphases facadeTblcontestphases;

    @PostConstruct
    public void init() {

    }

    public void updateContests() throws MalformedURLException, IOException {
        URL url;
        URLConnection con;
        InputStream in;
        try {
            // API URL To Get All Contests "GYM Contests Excluded"
            url = new URL("http://codeforces.com/api/contest.list?gym=false");
            con = url.openConnection();
            in = con.getInputStream();
        } catch (Exception e) {
            sendMessageInDialog("Error", "Couldn't Connect to Codeforces, Try Again Later", Severity.Error);
            return;
        }
        // Convert Result to String
        String result = read(in);
        // Create JSON Object
        JSONObject obj = new JSONObject(result);
        // Check Status
        String stts = (String) obj.get("status");
        System.out.println("Status : " + stts);
        if (stts.equals("FAILED")) {
            sendMessageInDialog("Error", "Request Failed, Some Error Occured", Severity.Error);
            return;
        }
        // Retrieve Contests Array
        JSONArray retrievedContests = (JSONArray) obj.get("result");
        // Check if No New Contests
        if (retrievedContests.length() == facadeTblcontests.count()) {
            sendMessageInDialog("Information", "There Is No New Contests", Severity.Info);
            return;
        }
        for (int i = 0; i < retrievedContests.length(); i++) {
            JSONObject contest = (JSONObject) retrievedContests.get(i);
            Tblcontests tblcontests = new Tblcontests();
            // Get Contest ID
            tblcontests.setContestCFID(contest.getInt("id"));
            // Check if Contest Exist
            if (facadeTblcontests.findContestByCodeForcesID(tblcontests.getContestCFID()) != null) {
                continue;
            }
            // Get Contest Name
            tblcontests.setContestName(contest.getString("name"));
            // Get Contest Type
            String type = contest.getString("type");
            Tblcontesttypes contestType = facadeTblcontesttypes.findContestTypeByName(type);
            tblcontests.setContestType(contestType);
            // Get Contest Phase
            String phase = contest.getString("phase");
            Tblcontestphases contestPhase = facadeTblcontestphases.findPhaseByName(phase);
            tblcontests.setContestPhase(contestPhase);
            // Get Contest Frozen
            boolean isFrozen = contest.getBoolean("frozen");
            if (isFrozen) {
                tblcontests.setIsFrozen(1);
            } else {
                tblcontests.setIsFrozen(0);
            }
            // Get Contest Duration
            tblcontests.setContestDurationInSeconds(contest.getLong("durationSeconds"));
            // Get Contest Start Time In Seconds "IF Exist"
            if (contest.has("startTimeSeconds")) {
                tblcontests.setContestStartTime(contest.getLong("startTimeSeconds"));
            }
            // Get Contest Difficulty "IF Exist"
            if (contest.has("difficulty")) {
                tblcontests.setContestDifficulty(contest.getInt("difficulty"));
            }
            // Get Contest Kind "IF Exist"
            if (contest.has("kind")) {
                tblcontests.setContestKind(contest.getString("kind"));
            }
            // Get ICPC Region "IF Exist"
            if (contest.has("icpcRegion")) {
                tblcontests.setICPCRegion(contest.getString("icpcRegion"));
            }
            // Get Country "IF Exist"
            if (contest.has("country")) {
                tblcontests.setContestCountry(contest.getString("country"));
            }
            // Assign URL
            tblcontests.setContestURL("http://codeforces.com/contest/" + tblcontests.getContestCFID());

            // Confirm Object Creation
            System.out.println("Contest #" + tblcontests.getContestCFID() + " With Name : " + tblcontests.getContestName() + " Created.");
            facadeTblcontests.create(tblcontests);
            System.out.println("Added");
        }
        sendMessageInDialog("Information", "Contests Updated Successfully", Severity.Info);
    }

    public List<Tblcontests> findAllContests() {
        return facadeTblcontests.findAll();
    }

}
