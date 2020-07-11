/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.utils.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author adel.ewis
 */
public class RecommenderEngine {

    //Russell & Rao coefficient
    public double sim_RR(double[][] profs, int person1, int person2) {
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < profs[person1].length; i++) {
            if ((profs[person1][i] == 1) && (profs[person2][i] == 1)) { // change -1 to 0
                a++;
            } else if ((profs[person1][i] == 1) && (profs[person2][i] == 0)) {
                b++;
            } else if ((profs[person1][i] == 0) && (profs[person2][i] == 1)) {
                c++;
            } else if ((profs[person1][i] == 0) && (profs[person2][i] == 0)) {
                d++;
            }
        }
//        System.out.println(a+" "+b+" "+c);
        double r;
        r = (double) a / (double) (a + b + c + d);
//        System.out.println(r);
        return r;

    }

    //jaccard correlation coofiecent
    public double sim_Jaccard(HashMap<Integer, HashSet<Integer>> profs, HashSet<Integer> person1data, int person2) {
        //a = 11
        //b = 10
        //c = 01
        int a = 0, b = 0, c = 0;
        HashSet<Integer> person2data = new HashSet<>();
        person2data = profs.get(person2);

        for (int i : person1data) {
            if (person2data.contains(i) == true) {
                a++;
            } else {
                b++;
            }
        }
        c = person2data.size() - a;
//        System.out.println(a+" "+b+" "+c);
        double r;
        r = (double) a / (double) (a + b + c);
//        System.out.println(r);
        return r;

    }

    //pearson correlation coofiecent
    public double sim_pearson(double[][] profs, int person1, int person2) {
        ArrayList<Integer> movies = new ArrayList<Integer>();
        for (int i = 0; i < profs[person1].length; i++) {
            if ((profs[person1][i] > 0) && (profs[person2][i] > 0)) { // change -1 to 0
                movies.add(i);
//                System.out.print(i+" ");
            }
        }
        int n = movies.size();
        if (n == 0) {
            return 0;
        }

        double sum1 = 0, sum2 = 0, sum1sq = 0, sum2sq = 0, psum = 0;
        for (int i = 0; i < n; i++) {
            sum1 += profs[person1][movies.get(i)];
            sum2 += profs[person2][movies.get(i)];

            sum1sq += Math.pow(profs[person1][movies.get(i)], 2);
            sum2sq += Math.pow(profs[person2][movies.get(i)], 2);

            psum += (profs[person1][movies.get(i)]) * (profs[person2][movies.get(i)]);
        }

        double num = psum - ((sum1 * sum2) / n);
        double den = Math.sqrt((sum1sq - (Math.pow(sum1, 2) / n)) * (sum2sq - (Math.pow(sum2, 2) / n)));

        if (den == 0) {
            return 0;
        }

        double r = num / den;
        //System.out.println("\n"+r);
        return r;
    }

    // ranking similarity
    public Map<Double, Integer> Top_Matches(HashMap<Integer, HashSet<Integer>> prefs, int person) {

        Map<Double, Integer> scores = new TreeMap<Double, Integer>(Collections.reverseOrder());
        Map<Double, Integer> scores1 = new TreeMap<Double, Integer>(Collections.reverseOrder());

        HashSet<Integer> persondata = new HashSet<>();
        persondata = prefs.get(person);

        double check;

        for (Map.Entry<Integer, HashSet<Integer>> entry : prefs.entrySet()) {
            Integer key = entry.getKey();
            if (key != person) {
                check = sim_Jaccard(prefs, persondata, key); // jaccard instead of pearson
                if (check <= 0) {
                    continue;
                }
                scores.put(check, key); // check = similarity , key = index of the user
            }

        }

//        System.out.println(scores);
        int i = 0;
        for (Map.Entry<Double, Integer> entry : scores.entrySet()) {
            scores1.put(entry.getKey(), entry.getValue()); // scores1(index of the similarity, the user)
            if (i == 3) // return the four most similarity people
            {
                break;
            }
            i++;
        }
        System.out.println(scores1);

        return scores1;
    }

    // get the recommendation items
    public Map<Double, Integer> Get_Recommendation(HashMap<Integer, HashSet<Integer>> prefs, int person) {
        Map<Double, Integer> scores;
        // scores(index of the similarity, the user)
        scores = Top_Matches(prefs, person);

        HashSet<Integer> items = new HashSet<>();
        Map<Double, Integer> itemsUltimate = new TreeMap<Double, Integer>(Collections.reverseOrder());

        HashSet<Integer> persondata = new HashSet<>();
        persondata = prefs.get(person);  // person problems

        for (Map.Entry<Double, Integer> entry : scores.entrySet()) {
            Integer value = entry.getValue(); // user id
            Double key = entry.getKey();

            HashSet<Integer> scoresdata = new HashSet<>();
            scoresdata = prefs.get(value);
            for (int i : scoresdata) {
                if (persondata.contains(i) == false) {
                    items.add(i);
                }
            }
        }
        double simsum = 0;
        for (int i : items) {
            for (Map.Entry<Double, Integer> entry : scores.entrySet()) {
                Integer value = entry.getValue(); // user id
                Double key = entry.getKey();
                if (prefs.get(value).contains(i) == false) {
                    simsum += key;
                }
            }
            itemsUltimate.put(simsum, i);
            simsum = 0;
        }

//        for(int i=0;i<prefs.get(person).size();i++){
//            if(prefs[person][i]==0){ // items didn't solve
//                for(int j=0;j<scores.size();j++){
//                    double total1=0,simsum1=0;
//                    for(Map.Entry<Double,Integer> entry : scores.entrySet()){
//                        if(prefs[entry.getValue()][i] !=0){ // change the -1 to 0 (not solved or wrong answer)
//                            simsum1 += entry.getValue();
//                            total1 += entry.getKey() * prefs[entry.getValue()][i];
//                        }
//                    }
//                    if(!Double.isNaN(total1/simsum1)){
//                        // items contains (the index of the problem and the item weight)
//                        items.put((total1/simsum1) ,i);
//                    }
//                }
//            }
//        }
        return itemsUltimate;
    }
}
