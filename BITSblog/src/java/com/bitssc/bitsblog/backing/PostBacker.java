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
import com.ocpsoft.pretty.faces.annotation.URLBeanName;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
            pattern = "/post/#{postBacker.targetPostDate}/#{postBacker.targetPostTitle}/",
            viewId = "/faces/index.xhtml"),
    @URLMapping(
            id = "postsAdministration",
            pattern = "/admin/posts",
            viewId = "/faces/admin/posts.xhtml"
        ),
    @URLMapping(
            id = "newPost",
            pattern = "/admin/posts/newPost",
            viewId = "/faces/admin/posts.xhtml"
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
    private Boolean postListVisible = true;
    private Boolean postDetailsVisible;
    private Boolean postEditVisible;
    private String targetPostDate;
    private String targetPostTitle;
    private String editedPostStatus;

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
        }
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
    
    public Collection<PostStatus> getPostStatuses(){
        return statusProvider.getPostStatuses().values();
    }
    
    public List<Post> getAllPosts(){
        return postFacade.findAll();
    }
    
    public List<Post> getPinnedPosts(){
        return postFacade.findPinned();
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

    public Boolean getPostListVisible() {
        return postListVisible;
    }

    public Boolean getPostDetailsVisible() {
        return postDetailsVisible;
    }

    public Boolean getPostEditVisible() {
        return postEditVisible;
    }

    public String getEditedPostStatus() {
        return editedPostStatus;
    }

    public void setEditedPostStatus(String editedPostStatus) {
        this.editedPostStatus = editedPostStatus;
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
        
        postListVisible = false;
        postDetailsVisible = false;
        postEditVisible = true;
    }

    public void savePost() {
        currentPost.setDateLastModified(new Date());
        currentPost.setPostStatus(statusProvider.getPostStatuses().get(editedPostStatus));

        if (currentPost.getPostId() == null) {
            postManager.createPost(currentPost);
            getCurrentFacesContext().addMessage(null,new FacesMessage("Success",String.format("%s was created", currentPost.getTitle())));
        } else {
            postFacade.edit(currentPost);
            getCurrentFacesContext().addMessage(null, new FacesMessage("Success", String.format("%s was updated", currentPost.getTitle())));
        }
        showPostDetails(currentPost.getPostId());
    }

    public void cancelEdit() {
        if (currentPost.getPostId() != null) {
            showPostDetails(currentPost.getPostId());
        } else {
            showPostList();
        }
    }
}
