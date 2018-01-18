/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblmainmenu;
import java.util.List;
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
public class FacadeTblmainmenu extends AbstractFacade<Tblmainmenu> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblmainmenu() {
        super(Tblmainmenu.class);
    }

    public List<Tblmainmenu> findAllMenuesWithoutChildren() {
        TypedQuery<Tblmainmenu> q = em.createQuery("SELECT m FROM Tblmainmenu m WHERE m.pageID IS NOT NULL", Tblmainmenu.class);
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        }
        return null;
    }

    public List<Tblmainmenu> findAllMenuesWithChildren() {
        TypedQuery<Tblmainmenu> q = em.createQuery("SELECT m FROM Tblmainmenu m WHERE m.pageID IS NULL", Tblmainmenu.class);
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        }
        return null;
    }

    public int delete(Tblmainmenu mainMenu) {
        Query q = em.createQuery("DELETE FROM Tblmainmenu m WHERE m.menuID=:menuID");
        q.setParameter("menuID", mainMenu.getMenuID());
        return q.executeUpdate();
    }

}
