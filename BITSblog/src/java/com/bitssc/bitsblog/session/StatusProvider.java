/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.session;

import com.bitssc.bitsblog.entity.CommentStatus;
import com.bitssc.bitsblog.entity.PageStatus;
import com.bitssc.bitsblog.entity.PostStatus;
import com.bitssc.bitsblog.facade.CommentStatusFacade;
import com.bitssc.bitsblog.facade.PageStatusFacade;
import com.bitssc.bitsblog.facade.PostStatusFacade;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 *
 * @author RBroyles
 */
@Singleton
public class StatusProvider {
    @EJB
    private PageStatusFacade pageStatusFacade;
    @EJB
    private CommentStatusFacade commentStatusFacade;
    @EJB
    private PostStatusFacade postStatusFacade;
    
    private Map<String,PageStatus> pageStatuses;
    private Map<String,CommentStatus> commentStatuses;
    private Map<String,PostStatus> postStatuses;
    

    /**
     * Creates a new instance of GlobalListsProvider
     */
    public StatusProvider() {
    }
    
    @PostConstruct
    private void init(){
        pageStatuses = new HashMap<>();
        for(PageStatus status : pageStatusFacade.findAll()){
            pageStatuses.put(status.getName(), status);
        }
        
        commentStatuses = new HashMap<>();
        for(CommentStatus status : commentStatusFacade.findAll()){
            commentStatuses.put(status.getName(), status);
        }
        
        postStatuses = new HashMap<>();
        for(PostStatus status : postStatusFacade.findAll()){
            postStatuses.put(status.getName(), status);
        }
    }

    public Map<String,PageStatus> getPageStatuses() {
        return pageStatuses;
    }

    public Map<String,CommentStatus> getCommentStatuses() {
        return commentStatuses;
    }

    public Map<String,PostStatus> getPostStatuses() {
        return postStatuses;
    }
}
