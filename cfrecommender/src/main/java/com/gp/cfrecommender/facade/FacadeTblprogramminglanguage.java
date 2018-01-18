/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gp.cfrecommender.facade;

import com.gp.cfrecommender.model.Tblprogramminglanguage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed-Ali
 */
@Stateless
public class FacadeTblprogramminglanguage extends AbstractFacade<Tblprogramminglanguage> {

    @PersistenceContext(unitName = "com.gp_CFGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacadeTblprogramminglanguage() {
        super(Tblprogramminglanguage.class);
    }
    
}
