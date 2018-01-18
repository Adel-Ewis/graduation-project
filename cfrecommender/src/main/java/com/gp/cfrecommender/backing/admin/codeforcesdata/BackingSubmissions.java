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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author developer
 */
@Named
@ViewScoped
public class BackingSubmissions extends BaseBackingBean implements Serializable {

    @EJB
    FacadeTblusers facadeTblusers;

    String directoryToSaveSubmissionsFile;

    @PostConstruct
    public void init() {
        directoryToSaveSubmissionsFile = "";
    }

    public void clearMe() {

    }

    public void storeDirectories() {
        System.out.println(directoryToSaveSubmissionsFile);
        sendMessageInDialog("Information", "Directories Saved Successfully", Severity.Info);
    }

    public void fetchUserSubmission(String userHandle) throws IOException {
        if (directoryToSaveSubmissionsFile.isEmpty()) {
            sendMessageInDialog("Error", "Please Enter Directory to Save Submissions in First and Try Again", Severity.Error);
            return;
        }
        // Connect to API
        URL url = null;
        try {
            url = new URL("http://codeforces.com/api/user.status?handle=" + userHandle + "&from=1");
        } catch (MalformedURLException ex) {
            sendMessageInDialog("Error", "Couldn't Connect to Codeforces, Try Again Later", Severity.Error);
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException ex) {
            sendMessageInDialog("Error", "Couldn't Connect to Codeforces, Try Again Later", Severity.Error);
        }
        InputStream in = null;
        try {
            in = con.getInputStream();
        } catch (IOException ex) {
            sendMessageInDialog("Error", "Couldn't Connect to Codeforces, Try Again Later", Severity.Error);
        }
        // Store Response in Text File
        File file = new File(directoryToSaveSubmissionsFile + "/" + userHandle + ".txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            String response = read(in);
            if (response.isEmpty()) {
                sendMessageInDialog("Error", "Some Error Happend, Try Again Later", Severity.Error);
                return;
            }
            fileWriter.write(response);
            fileWriter.flush();
        }
        sendMessageInDialog("Information", "User Submission Fetched Successfully", Severity.Info);
    }

    public List<Tblusers> findAllTrainees() {
        return facadeTblusers.findAll();
    }

    public String getDirectoryToSaveSubmissionsFile() {
        return directoryToSaveSubmissionsFile;
    }

    public void setDirectoryToSaveSubmissionsFile(String directoryToSaveSubmissionsFile) {
        this.directoryToSaveSubmissionsFile = directoryToSaveSubmissionsFile;
    }

}
