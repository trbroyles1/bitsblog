/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.facade;

import com.bitssc.bitsblog.entity.PostAuthor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RBroyles
 */
@Stateless
public class PostAuthorFacade extends AbstractFacade<PostAuthor> {
    @PersistenceContext(unitName = "BITSblogPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostAuthorFacade() {
        super(PostAuthor.class);
    }
    
    public PostAuthor findByUserName(String userName){
        Query postAuthorQuery = em.createNamedQuery("PostAuthor.findByUserName");
        postAuthorQuery.setParameter("userName", userName);
        return (PostAuthor)postAuthorQuery.getSingleResult();
        
    }
    
}
