/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.session;

import com.bitssc.bitsblog.entity.CommentStatus;
import com.bitssc.bitsblog.entity.PageStatus;
import com.bitssc.bitsblog.entity.PostStatus;
import com.bitssc.bitsblog.entity.Setting;
import com.bitssc.bitsblog.entity.Tag;
import com.bitssc.bitsblog.facade.CommentStatusFacade;
import com.bitssc.bitsblog.facade.PageStatusFacade;
import com.bitssc.bitsblog.facade.PostStatusFacade;
import com.bitssc.bitsblog.facade.SettingFacade;
import com.bitssc.bitsblog.facade.TagFacade;
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
public class ListsProvider {
    @EJB
    private SettingFacade settingFacade;
    
    @EJB
    private TagFacade tagFacade;
    
    @EJB
    private PageStatusFacade pageStatusFacade;
    @EJB
    private CommentStatusFacade commentStatusFacade;
    @EJB
    private PostStatusFacade postStatusFacade;
    
    
    
    private Map<String,PageStatus> pageStatuses;
    private Map<String,CommentStatus> commentStatuses;
    private Map<String,PostStatus> postStatuses;
    private Map<String, Setting> settings;
    private Map<String, Tag> postTags;
    

    /**
     * Creates a new instance of GlobalListsProvider
     */
    public ListsProvider() {
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
        
        settings = new HashMap<>();
        for(Setting s : settingFacade.findAll()){
            settings.put(s.getSettingKey(),s);
        }
        
        postTags = new HashMap<>();
        for (Tag t : tagFacade.findAll()){
            postTags.put(t.getName(), t);
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
    
    public Map<String,Tag> getPostTags(){
        return postTags;
    }
    
    public Map<String,Setting> getSettings(){
        return settings;
    }
}
