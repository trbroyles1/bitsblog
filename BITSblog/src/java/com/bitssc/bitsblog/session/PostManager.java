/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.session;

import com.bitssc.bitsblog.entity.Post;
import com.bitssc.bitsblog.facade.PostAuthorFacade;
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

/**
 *
 * @author RBroyles
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PostManager {
    @EJB
    private ListsProvider statusProvider;
    
    @EJB
    private PostFacade postFacade;
    @EJB
    private PostAuthorFacade postAuthorFacade;
    
    @Resource
    private SessionContext context;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int createPost(Post post, String authorUserName) {
        try{
        post.setPostAuthor(postAuthorFacade.findByUserName(authorUserName));
        post.setDateCreated(new Date());
        postFacade.create(post);
        
        return 0;
        }
        catch(Exception e)
        {
            context.setRollbackOnly();
            return -1;
        }
    }
    public void postViewHit(Post post){
        post.setNumberOfViews(post.getNumberOfViews() + 1);
        postFacade.edit(post);
    }

}
