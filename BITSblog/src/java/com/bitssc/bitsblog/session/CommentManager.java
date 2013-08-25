/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.session;

import com.bitssc.bitsblog.entity.Comment;
import com.bitssc.bitsblog.entity.CommentAuthor;
import com.bitssc.bitsblog.entity.Post;
import com.bitssc.bitsblog.facade.CommentAuthorFacade;
import com.bitssc.bitsblog.facade.CommentFacade;
import com.bitssc.bitsblog.facade.CommentStatusFacade;
import com.bitssc.bitsblog.facade.PostFacade;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author RBroyles
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CommentManager {

    @PersistenceContext(unitName = "BITSblogPU")
    private EntityManager em;
    @EJB
    private CommentFacade commentFacade;
    @EJB
    private PostFacade postFacade;
    @EJB
    private CommentStatusFacade commentStatusFacade;
    @EJB
    private CommentAuthorFacade commentAuthorFacade;
    @EJB
    private StatusProvider statusProvider;
    @Resource
    private SessionContext context;

    public static enum AddResult {

        SUCCESS,
        ERROR,
        AUTHOR_BANNED,
        AUTHOR_APPROVAL_REQUIRED,
        POST_APPROVAL_REQUIRED
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public AddResult addComment(String author, String email, String body, int postId) {
        try {
            CommentAuthor a = createOrRetrieveCommentAuthor(author, email);
            if (a.getBanned()) {
                context.setRollbackOnly();
                return AddResult.AUTHOR_BANNED;
            }

            return createComment(postFacade.find(postId), a, body);

        } catch (Exception e) {
            context.setRollbackOnly();
            return AddResult.ERROR;
        }
    }

    public void approveComment(int commentId) {
        Comment toApprove = commentFacade.find(commentId);
        toApprove.setCommentStatus(statusProvider.getCommentStatuses().get(Comment.STATUS_APPROVED));
        commentFacade.edit(toApprove);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int banCommentAuthor(int authorId) {
        try {
            CommentAuthor author = commentAuthorFacade.find(authorId);
            author.setBanned(true);
            commentAuthorFacade.edit(author);
            for (Comment c : author.getCommentList()) {
                censorComment(c.getCommentId());
            }
            
            return 0;
        }
        catch (Exception e){
            context.setRollbackOnly();
            return -1;
        }

    }

    public void censorComment(int commentId) {
        Comment c = commentFacade.find(commentId);
        c.setCommentStatus(statusProvider.getCommentStatuses().get(Comment.STATUS_CENSORED));
        commentFacade.edit(c);
    }

    private CommentAuthor createOrRetrieveCommentAuthor(String name, String email) {
        CommentAuthor author = null;
        Query authorQuery = em.createNamedQuery("CommentAuthor.findByEmailAddress");
        authorQuery.setParameter("emailAddress", email);
        try {
            author = (CommentAuthor) authorQuery.getSingleResult();
        } catch (NoResultException e) {
            author = new CommentAuthor();
            author.setName(name);
            author.setEmailAddress(email);
            author.setDateCreated(new Date());

            em.persist(author);
            em.flush();
        }

        return author;
    }

    private AddResult createComment(Post post, CommentAuthor author, String body) {
        Comment comment = new Comment();
        comment.setPostId(post); //TODO: fix the method name on the comment entity!
        comment.setCommentAuthor(author);
        comment.setBody(body);
        comment.setDateCreated(new Date());

        //TODO: Is there a better way of setting status on new comments?
        if (author.getCommentApprovalRequired() || post.getCommentApprovalRequired()) {
            comment.setCommentStatus(statusProvider.getCommentStatuses().get(Comment.STATUS_PENDING));
        } else {
            comment.setCommentStatus(statusProvider.getCommentStatuses().get(Comment.STATUS_APPROVED));
        }

        em.persist(comment);
        em.flush();
        post.getCommentList().add(0, comment);

        if (author.getCommentApprovalRequired()) {
            return AddResult.AUTHOR_APPROVAL_REQUIRED;
        }
        if (post.getCommentApprovalRequired()) {
            return AddResult.POST_APPROVAL_REQUIRED;
        }

        return AddResult.SUCCESS;
    }
}
