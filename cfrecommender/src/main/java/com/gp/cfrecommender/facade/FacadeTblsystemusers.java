/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblsystemusers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblsystemusers extends AbstractFacade<Tblsystemusers> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblsystemusers() {
        super(Tblsystemusers.class);
    }

    public Tblsystemusers checkIfUserExist(String userName, String password) {
        TypedQuery<Tblsystemusers> q = em.createQuery("SELECT u FROM Tblsystemusers u WHERE u.userName=:uName AND u.password=:pass", Tblsystemusers.class);
        q.setParameter("uName", userName);
        q.setParameter("pass", password);
        if (!q.getResultList().isEmpty()) {
            return q.getSingleResult();
        }
        return null;
    }

    public Tblsystemusers findUserByUserName(String uName) {
        TypedQuery<Tblsystemusers> q = em.createQuery("SELECT u FROM Tblsystemusers u WHERE u.userName=:uName", Tblsystemusers.class);
        q.setParameter("uName", uName);
        if (!q.getResultList().isEmpty()) {
            return q.getSingleResult();
        }
        return null;
    }

    public void deleteSystemUser(long userID) {
        Query q = em.createQuery("DELETE FROM Tblsystemusers u WHERE u.systemUserID=:userID");
        q.setParameter("userID", userID);
        q.executeUpdate();
    }

}
