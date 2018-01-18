/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblmainmenu;
import com.gp.cfrecommender.model.Tblsubmenu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblsubmenu extends AbstractFacade<Tblsubmenu> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblsubmenu() {
        super(Tblsubmenu.class);
    }

    public int deleteSubMenu(Tblsubmenu subMenu) {
        Query q = em.createQuery("DELETE FROM Tblsubmenu s WHERE s.subMenuID=:subMenuID");
        q.setParameter("subMenuID", subMenu.getSubMenuID());
        return q.executeUpdate();
    }

}
