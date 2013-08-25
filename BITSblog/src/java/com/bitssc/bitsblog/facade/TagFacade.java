/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.facade;

import com.bitssc.bitsblog.entity.Tag;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author RBroyles
 */
@Stateless
public class TagFacade extends AbstractFacade<Tag> {
    @PersistenceContext(unitName = "BITSblogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }
    
}
