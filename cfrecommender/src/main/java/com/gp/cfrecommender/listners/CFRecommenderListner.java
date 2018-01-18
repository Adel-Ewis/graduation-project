package com.gp.cfrecommender.listners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author mohamed
 */
public class CFRecommenderListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("==================================");
        System.out.println("======== Context Starting ========");
        System.out.println("==================================");
        DataModel dataModel = null;
        HashMap<Integer, HashSet<Integer>> matrixData = new HashMap<>();
        try {
            dataModel = new FileDataModel(new File("/home/mostafa_thabet/NetBeansProjects/graduationproject/matrices/mahout-format-binary.csv"));
            ArrayList<Integer> users = getUSersIDs(dataModel);
            for (int user : users) {
                HashSet<Integer> put = new HashSet<>(getUserPref(user, dataModel));
                matrixData.put(user, put);
            }

        } catch (IOException ex) {
            Logger.getLogger(CFRecommenderListner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TasteException ex) {
            Logger.getLogger(CFRecommenderListner.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map<String, Object> applicationMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        applicationMap.put("matrixData", matrixData);
        System.out.println("===================================");
        System.out.println("== Matrix Data Putted in Context ==");
        System.out.println("===================================");
        applicationMap.put("dataModel", dataModel);
        System.out.println("===================================");
        System.out.println("== Data Model Putted in Context ==");
        System.out.println("===================================");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("==================================");
        System.out.println("========= Context Closing ========");
        System.out.println("==================================");
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
