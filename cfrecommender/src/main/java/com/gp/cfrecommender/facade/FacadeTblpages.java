/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblmainmenu;
import com.gp.cfrecommender.model.Tblpages;
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
public class FacadeTblpages extends AbstractFacade<Tblpages> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblpages() {
        super(Tblpages.class);
    }

    public Tblpages findPageByName(String pageName) {
        TypedQuery<Tblpages> q = em.createQuery("SELECT p FROM Tblpages p WHERE p.pageFileName=:pName", Tblpages.class);
        q.setParameter("pName", pageName);
        if (!q.getResultList().isEmpty()) {
            return q.getSingleResult();
        }
        return null;
    }

    public List<Tblpages> findAllPagesNotInMenu(Tblmainmenu mainMenu) {
        TypedQuery<Tblpages> q = em.createQuery("SELECT page FROM Tblpages page WHERE page.pageID NOT IN (SELECT sub.pageID.pageID FROM Tblsubmenu sub WHERE sub.parentMenuID.menuID=:parentID ) ORDER BY page.pageMenuName", Tblpages.class);
        q.setParameter("parentID", mainMenu.getMenuID());
        if (q.getResultList().isEmpty()) {
            return new ArrayList<>();
        }
        return q.getResultList();
    }

}
