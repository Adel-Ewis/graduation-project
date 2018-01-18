/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.backing.admin.codeforcesdata;

import com.gp.cfrecommender.backing.BaseBackingBean;
import com.gp.cfrecommender.enums.Severity;
import com.gp.cfrecommender.facade.FacadeTblusers;
import com.gp.cfrecommender.model.Tblusers;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
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
public class BackingUsers extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblusers facadeTblusers;

    @PostConstruct
    public void init() {

    }

    public void updateUsers() throws IOException {
        List<Tblusers> allUsers = facadeTblusers.findAll();
        int size = allUsers.size();
        System.out.println("Users : " + size);
        URL url;
        URLConnection con;
        InputStream in;
        for (int i = 0; i < size; i++) {
            Tblusers user = allUsers.get(i);
            // Generate API URL for User
            try {
                url = new URL("http://codeforces.com/api/user.info?handles=" + user.getUserHandle());
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
            System.out.println(user.getUserID() + " STATUS : " + stts);
            if (stts.equals("FAILED")) {
                // Retrieve Users Array
                JSONArray retrievedUsers = (JSONArray) obj.get("result");
                // Get First User in Array "Usuall Array Contains only One User"
                JSONObject jsonObject = retrievedUsers.getJSONObject(0);
                // Assign Data to Object
                if (jsonObject.has("country")) {
                    user.setCountry(jsonObject.getString("country"));
                }
                user.setContribution(jsonObject.getInt("contribution"));
                user.setUserRank(jsonObject.getString("rank"));
                user.setUserRating(jsonObject.getInt("rating"));
                user.setMaxRank(jsonObject.getString("maxRank"));
                user.setMaxRating(jsonObject.getInt("maxRating"));
                user.setRegistrationTimeInSeconds(jsonObject.getBigInteger("registrationTimeSeconds"));
                // Update Object
                facadeTblusers.edit(user);
                System.out.println("User Updated !!");
            }
        }
        sendMessageInDialog("Information", "All Users Updated Successfully", Severity.Info);
    }

    public List<Tblusers> findAllUsers() {
        return facadeTblusers.findAll();
    }

}
