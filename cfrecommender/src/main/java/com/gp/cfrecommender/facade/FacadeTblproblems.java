/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblproblems;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblproblems extends AbstractFacade<Tblproblems> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblproblems() {
        super(Tblproblems.class);
    }
    
    public List<Tblproblems> findAllProblemsSortedBySolvedCount() {
        TypedQuery<Tblproblems> q = em.createQuery("SELECT p FROM Tblproblems p ORDER BY p.solvedCount DESC",Tblproblems.class);
        if(q.getResultList().isEmpty()) {
            return new ArrayList<>();
        } else {
            return q.getResultList();
        }
    }

    public Tblproblems findProblemByContestIdAndIndex(String index, int contestID) {
        TypedQuery<Tblproblems> q = em.createQuery("SELECT p FROM Tblproblems p WHERE p.contestID.contestCFID=:cfid AND p.problemIndex=:index", Tblproblems.class);
        q.setParameter("cfid", contestID);
        q.setParameter("index", index);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList().get(0);
    }

}
