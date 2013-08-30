/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.bitssc.bitsblog.entity.Post;
import com.bitssc.bitsblog.entity.PostStatus;
import com.bitssc.bitsblog.entity.Tag;
import com.bitssc.bitsblog.facade.PostFacade;
import com.bitssc.bitsblog.session.PostManager;
import com.bitssc.bitsblog.session.ListsProvider;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            pattern = "/post/#{postBacker.targetPostDate}/#{postBacker.targetPostTitle}",
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
        ),
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
    private Boolean postListVisible = true;
    private Boolean postDetailsVisible;
    private Boolean postEditVisible;
    private String targetPostDate;
    private String targetPostTitle;
    private String targetPostTag;
    private String editedPostStatus;
    private String editedPostTags;

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
    
    public String getPostTags(){
        return String.format("'%s'", StringUtils.join(statusProvider.getPostTags().keySet().toArray(), "','"));
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
    
    public String getEditedPostTags(){
        return editedPostTags;
    }
    
    public void setEditedPostTags(String value){
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
        for(Tag t : currentPost.getTagList())
        {
            tags.add(t.getName());
        }
        editedPostTags= StringUtils.join(tags,",");
        
        postListVisible = false;
        postDetailsVisible = false;
        postEditVisible = true;
    }

    public void savePost() {
        currentPost.setDateLastModified(new Date());
        currentPost.setPostStatus(statusProvider.getPostStatuses().get(editedPostStatus));

        if (currentPost.getPostId() == null) {
            postManager.createPost(currentPost, getCurrentFacesContext().getExternalContext().getUserPrincipal().getName(),editedPostTags);
            getCurrentFacesContext().addMessage(null,new FacesMessage("Success",String.format("%s was created", currentPost.getTitle())));
        } else {
            postManager.editPost(currentPost, editedPostTags);
            getCurrentFacesContext().addMessage(null, new FacesMessage("Success", String.format("%s was updated", currentPost.getTitle())));
        }
        showPostDetails(currentPost.getPostId());
    }
    
    public void deletePost(int postId){
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
