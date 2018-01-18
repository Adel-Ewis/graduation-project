/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblproblemtypes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblproblemtypes extends AbstractFacade<Tblproblemtypes> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblproblemtypes() {
        super(Tblproblemtypes.class);
    }

    public Tblproblemtypes findTypeByNameOrCreateIt(String name) {
        TypedQuery<Tblproblemtypes> q = em.createQuery("SELECT t FROM Tblproblemtypes t WHERE t.typeName=:name", Tblproblemtypes.class);
        q.setParameter("name", name);
        if (q.getResultList().isEmpty()) {
            Tblproblemtypes tblproblemtypes = new Tblproblemtypes();
            tblproblemtypes.setTypeName(name);
            tblproblemtypes.setTypeDescription(name);
            this.create(tblproblemtypes);
            return tblproblemtypes;
        }
        return q.getResultList().get(0);
    }

}
