/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.codeforcesdata;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblcontests;
import com.gp.cfrecommender.facade.FacadeTblproblems;
import com.gp.cfrecommender.facade.FacadeTblproblemtags;
import com.gp.cfrecommender.facade.FacadeTblproblemtagsbridge;
import com.gp.cfrecommender.facade.FacadeTblproblemtypes;
import com.gp.cfrecommender.model.Tblcontests;
import com.gp.cfrecommender.model.Tblproblems;
import com.gp.cfrecommender.model.Tblproblemtags;
import com.gp.cfrecommender.model.Tblproblemtagsbridge;
import com.gp.cfrecommender.model.Tblproblemtypes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author mohamed
 */
@Named
@ViewScoped
public class BackingProblems extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblproblems facadeTblproblems;
    @EJB
    FacadeTblcontests facadeTblcontests;
    @EJB
    FacadeTblproblemtags facadeTblproblemtags;
    @EJB
    FacadeTblproblemtypes facadeTblproblemtypes;
    @EJB
    FacadeTblproblemtagsbridge facadeTblproblemtagsbridge;

    @PostConstruct
    public void init() {

    }

    public void updateProblems() throws MalformedURLException, IOException, Exception {
        // Get All Contests
        List<Tblcontests> allContests = facadeTblcontests.findAll();
        for (int i = 0; i < allContests.size(); i++) {
            Tblcontests contest = allContests.get(i);
            if (!contest.getTblproblemsList().isEmpty()) {
                continue;
            }
            if (contest.getContestPhase().getPhaseName().equals("BEFORE")) {
                continue;
            }
            URL url;
            URLConnection con;
            InputStream in = null;
            // API URL To Get All Contest Problems
            try {
                url = new URL("http://codeforces.com/api/contest.standings?contestId=" + contest.getContestCFID() + "&from=1&count=1");
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
                sendMessageInDialog("Error", "Request Failed, Try Again Later", Severity.Error);
                return;
            }
            JSONObject problemsResult = obj.getJSONObject("result");
            // Get All Problems
            JSONArray allProblems = (JSONArray) problemsResult.get("problems");
            // Validate Number of Problems Found For Contest
            System.out.println("Contest #" + contest.getContestCFID() + " : " + allProblems.length() + " Problems Found.");
            for (int j = 0; j < allProblems.length(); j++) {
                JSONObject problem = (JSONObject) allProblems.get(j);
                Tblproblems tblproblems = new Tblproblems();
                // Set Problem Contest
                tblproblems.setContestID(contest);
                // Set Problem Index
                tblproblems.setProblemIndex(problem.getString("index"));
                // Set Problem Name
                tblproblems.setProblemName(problem.getString("name"));
                // Set Problem Type
                String type = problem.getString("type");
                Tblproblemtypes problemType = facadeTblproblemtypes.findTypeByNameOrCreateIt(type);
                tblproblems.setProblemType(problemType);
                // Set Problem Points "IF Exist"
                if (problem.has("points")) {
                    tblproblems.setProblemPoints(problem.getDouble("points"));
                }
                // Find All Problem Tags
                JSONArray problemTags = problem.getJSONArray("tags");
                List<Tblproblemtags> allProblemTags = new ArrayList<>();
                for (int k = 0; k < problemTags.length(); k++) {
                    String tagName = problemTags.getString(k);
                    // Get Tag if Exist or Add it To Database
                    Tblproblemtags problemTag = facadeTblproblemtags.findTagByNameOrCreateIt(tagName);
                    // Add Tag to Problem Tags List
                    allProblemTags.add(problemTag);
                }
                // Assign Problem URL
                tblproblems.setProblemURL(contest.getContestURL() + "/problem/" + tblproblems.getProblemIndex());
                // Add Problem to Database
                facadeTblproblems.create(tblproblems);
                // Assign Tags to the Problem in Database
                for (Tblproblemtags allProblemTag : allProblemTags) {
                    Tblproblemtagsbridge tblproblemtagsbridge = new Tblproblemtagsbridge();
                    tblproblemtagsbridge.setProblemID(tblproblems);
                    tblproblemtagsbridge.setTagID(allProblemTag);
                    // Create Problem Tag Bridge
                    facadeTblproblemtagsbridge.create(tblproblemtagsbridge);
                }
            }
        }
        updateProblemSolvedCount();
        sendMessageInDialog("Information", "Problems Updated Successfully", Severity.Info);
    }

    public void updateProblemSolvedCount() throws MalformedURLException, IOException, Exception {
        // Get All Contests
        List<Tblcontests> allContests = facadeTblcontests.findAll();
        for (int k = 1; k < allContests.size(); k++) {
            // Get Contest
            Tblcontests contest = allContests.get(k);
            Document doc;
            // Get Contest Page
            try {
                doc = Jsoup.connect("http://codeforces.com/contest/" + contest.getContestCFID()).get();
            } catch (Exception e) {
                sendMessageInDialog("Error", "Couldn't Connect to Codeforces to Get Solved Count, Try Again Later", Severity.Error);
                return;
            }
            // Get Page Content Div
            Element pageContent = doc.getElementById("pageContent");
            // Get Problems Table
            Elements allDataTables = pageContent.select(".problems");
            Element problemsTable = allDataTables.get(0);
            // Get Problems Table Rows
            Elements allRows = problemsTable.getElementsByTag("tr");
            // Verify Number of Rows
            System.out.println("Contest #" + contest.getContestCFID() + " #" + (allRows.size() - 1) + " Problems Found.");
            for (int i = 1; i < allRows.size(); i++) {
                Element row = allRows.get(i);
                // Get Problem Index from Row
                Elements problemIndexColumn = row.select(".id");
                System.out.println("Problem Index : " + problemIndexColumn.text());
                // Ger Problem Object from Database
                Tblproblems problem = facadeTblproblems.findProblemByContestIdAndIndex(problemIndexColumn.text(), contest.getContestCFID());
                // Get Solved Count From Row
                Elements solvedCountColumn = row.getElementsByAttributeValue("style", "font-size: 1.1rem;");
                String count = solvedCountColumn.text();
                String counter = removeNotDigit(count);
                // Assign Solved Count to Problem Object
                if (counter.length() != 0) {
                    problem.setSolvedCount(Integer.valueOf(counter));
                } else {
                    problem.setSolvedCount(0);
                }
                System.out.println("Solved Count : " + counter);
                // Update Object in Database
                facadeTblproblems.edit(problem);
                System.out.println("Problem Updated.");
            }
        }
    }

    private String removeNotDigit(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                result += c;
            }
        }
        return result;
    }

    public List<Tblproblems> findAllProblems() {
        return facadeTblproblems.findAll();
    }
}
