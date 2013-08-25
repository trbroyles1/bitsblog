/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.facade;

import com.bitssc.bitsblog.entity.Comment;
import com.bitssc.bitsblog.session.ListsProvider;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RBroyles
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> {
    @EJB
    private ListsProvider statusProvider;

    @PersistenceContext(unitName = "BITSblogPU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }
    
    public List<Comment> findAllForPost(int postId){
        Query query = em.createNamedQuery("Comment.findByPostId");
        query.setParameter("postId", postId);
        return query.getResultList();
    }

    public List<Comment> findApprovedCommentsForPost(int postId) {
        List<Comment> comments;
        Query query = em.createNamedQuery("Comment.findByStatusNameAndPostId");
        query.setParameter("commentStatusName", Comment.STATUS_APPROVED);
        query.setParameter("postId", postId);
        comments =  query.getResultList();
        Collections.sort(
                comments,
                new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return (o1.getCommentId().compareTo(o2.getCommentId()) * -1);
            }
        });
        
        return comments;

    }
    
    public List<Comment> findAllPendingComments(){
        List<Comment> comments;
        Query pendingCommentsQuery = em.createNamedQuery("Comment.findByStatus");
        pendingCommentsQuery.setParameter("commentStatusName", Comment.STATUS_PENDING);
        comments = pendingCommentsQuery.getResultList();
        
        Collections.sort(
                comments,
                new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return (o1.getCommentId().compareTo(o2.getCommentId()) * -1);
            }
        });
        
        return comments;
    }
}
