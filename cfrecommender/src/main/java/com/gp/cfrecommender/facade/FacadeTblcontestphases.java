/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblcontestphases;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblcontestphases extends AbstractFacade<Tblcontestphases> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblcontestphases() {
        super(Tblcontestphases.class);
    }

    public Tblcontestphases findPhaseByName(String name) {
        TypedQuery<Tblcontestphases> q = em.createQuery("SELECT p FROM Tblcontestphases p WHERE p.phaseName=:name", Tblcontestphases.class);
        q.setParameter("name", name);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList().get(0);
    }

}
