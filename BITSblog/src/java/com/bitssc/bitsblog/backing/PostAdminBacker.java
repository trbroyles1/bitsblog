/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.bitssc.bitsblog.entity.Post;
import com.bitssc.bitsblog.entity.PostStatus;
import com.bitssc.bitsblog.entity.Tag;
import com.bitssc.bitsblog.facade.PostFacade;
import com.bitssc.bitsblog.session.ListsProvider;
import com.bitssc.bitsblog.session.PostManager;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author RBroyles
 */
@Named(value = "postAdminBacker")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(
            id = "postsAdministration",
            pattern = "/admin/posts",
            viewId = "/faces/admin/posts.xhtml"
        ),
    @URLMapping(
            id = "newPost",
            pattern = "/admin/posts/newPost",
            viewId = "/faces/admin/posts.xhtml"
        ),
})
public class PostAdminBacker {
    @EJB
    private ListsProvider listsProvider;
    @EJB
    private PostManager postManager;
    @EJB
    private PostFacade postFacade;
    
    
    

    private Post currentPost;
    private Boolean postListVisible = true;
    private Boolean postDetailsVisible;
    private Boolean postEditVisible;
    private String editedPostStatus;
    private String editedPostTags;

    /**
     * Creates a new instance of PostAdminBacker
     */
    public PostAdminBacker() {
    }
    
    @URLAction(mappingId = "newPost")
    public void initNewPost(){
        currentPost = new Post();
        postListVisible = false;
        postDetailsVisible = false;
        postEditVisible = true;
    }
    
    private FacesContext getCurrentFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    
    public Post getCurrentPost() {
        return currentPost;
    }

    private void setCurrentPost(Post value) {
        currentPost = value;
    }

    public Boolean getPostListVisible() {
        return postListVisible;
    }

    public Boolean getPostDetailsVisible() {
        return postDetailsVisible;
    }

    public Boolean getPostEditVisible() {
        return postEditVisible;
    }
    
    public Collection<PostStatus> getPostStatuses(){
        return listsProvider.getPostStatuses().values();
    }

    public String getEditedPostStatus() {
        return editedPostStatus;
    }

    public void setEditedPostStatus(String editedPostStatus) {
        this.editedPostStatus = editedPostStatus;
    }
    
    public String getPostTags(){
        return String.format("'%s'", StringUtils.join(listsProvider.getPostTags().keySet().toArray(), "','"));
    }

    public String getEditedPostTags() {
        return editedPostTags;
    }

    public void setEditedPostTags(String value) {
        editedPostTags = value;
    }

    public void showPostList() {
        postListVisible = true;
        postDetailsVisible = false;
        postEditVisible = false;
    }

    public void showPostDetails(int postId) {
        currentPost = postFacade.find(postId);
        postDetailsVisible = true;
        postEditVisible = false;
        postListVisible = false;
    }

    public void showPostEdit(int postId) {
        if (currentPost == null || currentPost.getPostId() != postId) {
            currentPost = postFacade.find(postId);
        }

        editedPostStatus = currentPost.getPostStatus().getName();
        List<String> tags = new ArrayList<>();
        for (Tag t : currentPost.getTagList()) {
            tags.add(t.getName());
        }
        editedPostTags = StringUtils.join(tags, ",");

        postListVisible = false;
        postDetailsVisible = false;
        postEditVisible = true;
    }

    public void savePost() {
        currentPost.setDateLastModified(new Date());
        currentPost.setPostStatus(listsProvider.getPostStatuses().get(editedPostStatus));

        if (currentPost.getPostId() == null) {
            postManager.createPost(currentPost, getCurrentFacesContext().getExternalContext().getUserPrincipal().getName(), editedPostTags);
            getCurrentFacesContext().addMessage(null, new FacesMessage("Success", String.format("%s was created", currentPost.getTitle())));
        } else {
            postManager.editPost(currentPost, editedPostTags);
            getCurrentFacesContext().addMessage(null, new FacesMessage("Success", String.format("%s was updated", currentPost.getTitle())));
        }
        showPostDetails(currentPost.getPostId());
    }

    public void deletePost(int postId) {
        postFacade.remove(postFacade.find(postId));
        showPostList();
    }

    public void cancelEdit() {
        if (currentPost.getPostId() != null) {
            showPostDetails(currentPost.getPostId());
        } else {
            showPostList();
        }
    }
}
