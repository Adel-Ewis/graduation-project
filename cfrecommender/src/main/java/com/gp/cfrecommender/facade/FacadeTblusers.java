/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblusers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblusers extends AbstractFacade<Tblusers> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblusers() {
        super(Tblusers.class);
    }
    
    public Tblusers findCFUserByHandle(String handle){
        TypedQuery<Tblusers> q = em.createQuery("SELECT u FROM Tblusers u WHERE u.userHandle=:handle",Tblusers.class);
        q.setParameter("handle", handle);
        if(!q.getResultList().isEmpty()) {
            return q.getSingleResult();
        }
        return null;
    }
    
}
