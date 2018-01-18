/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblpages;
import com.gp.cfrecommender.model.Tblpermissions;
import com.gp.cfrecommender.model.Tblroles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblpermissions extends AbstractFacade<Tblpermissions> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblpermissions() {
        super(Tblpermissions.class);
    }

    public boolean checkIfPageHavePermission(Tblpages page, Tblroles role) {
        TypedQuery<Tblpermissions> q = em.createQuery("SELECT p FROM Tblpermissions p WHERE p.tblpages.pageID=:pageID AND p.tblroles.roleID=:roleID", Tblpermissions.class);
        q.setParameter("pageID", page.getPageID());
        q.setParameter("roleID", role.getRoleID());
        if (q.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }

}
