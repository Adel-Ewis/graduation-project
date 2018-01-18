/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblcontests;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblcontests extends AbstractFacade<Tblcontests> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblcontests() {
        super(Tblcontests.class);
    }

    public Tblcontests findContestByCodeForcesID(int contestID) {
        TypedQuery<Tblcontests> q = em.createQuery("SELECT c FROM Tblcontests c WHERE c.contestCFID=:cfid", Tblcontests.class);
        q.setParameter("cfid", contestID);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList().get(0);
    }

}
