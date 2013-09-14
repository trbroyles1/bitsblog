/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.bitssc.bitsblog.entity.Post;
import com.bitssc.bitsblog.entity.PostStatus;
import com.bitssc.bitsblog.facade.PostFacade;
import com.bitssc.bitsblog.session.PostManager;
import com.bitssc.bitsblog.session.ListsProvider;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author RBroyles
 */
@Named(value = "postBacker")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(
            id = "index",
            pattern = "/",
            viewId = "/faces/index.xhtml"),
    @URLMapping(
            id = "linkedPost",
            pattern = "/post/#{postBacker.targetPostDate}/#{postBacker.targetPostTitle}",
            viewId = "/faces/index.xhtml"),
    @URLMapping(
            id = "postsWithTag",
            pattern="/tag/#{postBacker.targetPostTag}",
            viewId = "/faces/postsWithTag.xhtml"
        )
})
public class PostBacker {
    @EJB
    private ListsProvider statusProvider;
    
    @EJB
    private PostManager postManager;

    @EJB
    private PostFacade postFacade;
    
    
    
    private Post olderPost;
    private Post currentPost;
    private Post newerPost;
    private String targetPostDate;
    private String targetPostTitle;
    private String targetPostTag;

    /**
     * Creates a new instance of PostBacker
     */
    public PostBacker() {
    }

    @URLAction(mappingId = "index")
    public void initCurrentPost() {
        List<Post> posts = postFacade.findNewest2Published();
        setCurrentPost(posts.size() > 0 ? posts.get(0) : null);
        olderPost = posts.size() > 1 ? posts.get(1) : null;
        
        postManager.postViewHit(currentPost);
    }

    @URLAction(mappingId = "linkedPost")
    public void initLinkedPost() throws ParseException {
        Post[] posts;
        if (targetPostDate != null && targetPostTitle != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            posts = postFacade.findPostAndSiblingsPublished(formatter.parse(targetPostDate), targetPostTitle);
            olderPost = posts[0];
            setCurrentPost(posts[1]);
            newerPost = posts[2];
            
            postManager.postViewHit(currentPost);
        }
    }
    
    public List<Post> getAllPosts(){
        return postFacade.findAll();
    }
    
    public List<Post> getPinnedPosts(){
        return postFacade.findPinned();
    }
    
    public List<Post> getTopViewedPosts(){
        return postFacade.findTopNByViews(5);
    }
    
    public List<Post> getTopCommentedPosts(){
        return postFacade.findTopNByComments(5);
    }
    
    public List<Post> getPostsForTargetTag(){
        return postFacade.findByTag(statusProvider.getPostTags().get(targetPostTag));
    }

    public Post getOlderPost() {
        return olderPost;

    }

    public Post getCurrentPost() {
        return currentPost;
    }

    private void setCurrentPost(Post value) {
        currentPost = value;
    }

    public Post getNewerPost() {
        return newerPost;
    }

    public String getTargetPostDate() {
        return targetPostDate;
    }

    public void setTargetPostDate(String targetPostDate) {
        this.targetPostDate = targetPostDate;
    }

    public String getTargetPostTitle() {
        return targetPostTitle;
    }

    public void setTargetPostTitle(String targetPostTitle) {
        this.targetPostTitle = targetPostTitle.replaceAll("-", " ");
    }
    
    public String getTargetPostTag(){
        return targetPostTag;
    }
    
    public void setTargetPostTag(String value){
        targetPostTag = value;
    }   
}
