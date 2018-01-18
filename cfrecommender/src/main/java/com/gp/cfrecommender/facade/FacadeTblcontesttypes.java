/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblcontesttypes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblcontesttypes extends AbstractFacade<Tblcontesttypes> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblcontesttypes() {
        super(Tblcontesttypes.class);
    }

    public Tblcontesttypes findContestTypeByName(String name) {
        TypedQuery<Tblcontesttypes> q = em.createQuery("SELECT t FROM Tblcontesttypes t WHERE t.typeName=:name", Tblcontesttypes.class);
        q.setParameter("name", name);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList().get(0);
    }

}
