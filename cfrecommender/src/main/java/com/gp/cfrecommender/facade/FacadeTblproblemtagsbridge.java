/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblproblemtagsbridge;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblproblemtagsbridge extends AbstractFacade<Tblproblemtagsbridge> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblproblemtagsbridge() {
        super(Tblproblemtagsbridge.class);
    }

    public boolean chekIfProbHaveTag(long problemID, int tagID) {
        TypedQuery<Tblproblemtagsbridge> q = em.createQuery("SELECT p FROM Tblproblemtagsbridge p WHERE p.problemID.problemID=:pID AND p.tagID.tagID=:tID", Tblproblemtagsbridge.class);
        q.setParameter("pID", problemID);
        q.setParameter("tID", tagID);
        if (q.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
}
