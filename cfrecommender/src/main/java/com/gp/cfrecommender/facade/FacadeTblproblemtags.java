/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblproblemtags;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblproblemtags extends AbstractFacade<Tblproblemtags> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblproblemtags() {
        super(Tblproblemtags.class);
    }

    public Tblproblemtags findTagByNameOrCreateIt(String name) {
        TypedQuery<Tblproblemtags> q = em.createQuery("SELECT t FROM Tblproblemtags t WHERE t.tagName=:name", Tblproblemtags.class);
        q.setParameter("name", name);
        if (q.getResultList().isEmpty()) {
            Tblproblemtags tblproblemtags = new Tblproblemtags();
            tblproblemtags.setTagName(name);
            this.create(tblproblemtags);
            return tblproblemtags;
        }
        return q.getResultList().get(0);
    }

    public void checkIfProblemHaveTagByProblemndTag(Long problemID, Integer tagID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
