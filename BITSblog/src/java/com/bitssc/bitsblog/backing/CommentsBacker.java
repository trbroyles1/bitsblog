/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.bitssc.bitsblog.entity.Comment;
import com.bitssc.bitsblog.facade.CommentFacade;
import com.bitssc.bitsblog.session.CommentManager;
import com.bitssc.bitsblog.session.ListsProvider;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author RBroyles
 */
@Named(value = "commentsBacker")
@ViewScoped
public class CommentsBacker {

    @EJB
    private CommentManager commentManager;
    @EJB
    private ListsProvider statusProvider;
    @EJB
    private CommentFacade commentFacade;
    private String newCommentAuthor = "";
    private String newCommentEmail = "";
    private String newCommentBody = "";

    /**
     * Creates a new instance of CommentsAdminBacker
     */
    public CommentsBacker() {
    }
    
    private FacesContext getCurrentFacesContext(){
        return FacesContext.getCurrentInstance();
    }

    public String getNewCommentAuthor() {
        return newCommentAuthor;
    }

    public void setNewCommentAuthor(String newCommentAuthor) {
        this.newCommentAuthor = newCommentAuthor;
    }

    public String getNewCommentEmail() {
        return newCommentEmail;
    }

    public void setNewCommentEmail(String newCommentEmail) {
        this.newCommentEmail = newCommentEmail;
    }

    public String getNewCommentBody() {
        return newCommentBody;
    }

    public void setNewCommentBody(String newCommentBody) {
        this.newCommentBody = newCommentBody;
    }

    public List<Comment> getApprovedComments(int postId) {
        return commentFacade.findApprovedCommentsForPost(postId);
    }
    
    public List<Comment> getAllPendingComments(){
        return commentFacade.findAllPendingComments();
    }
    
    public List<Comment> getAllCommentsForPost(int postId){
        List<Comment> commentsForPost = commentFacade.findAllForPost(postId);
        
        return commentsForPost;
    }

    public void postComment(int postId) {
        String userIp = ((HttpServletRequest)getCurrentFacesContext().getExternalContext().getRequest()).getRemoteAddr();
        
        CommentManager.AddResult addComment = commentManager.addComment(userIp, newCommentAuthor, newCommentEmail, newCommentBody, postId);
        FacesMessage resultMessage;
        switch(addComment){
            case SUCCESS:
                resultMessage = new FacesMessage( "Success", "Your comment has been posted");
                break;
            case AUTHOR_APPROVAL_REQUIRED:
                resultMessage = new FacesMessage( "Success", "Your comment has been received. Comment approval is turned on for your email. Your comment will be reviewed shortly.");
                break;
            case POST_APPROVAL_REQUIRED:
                resultMessage = new FacesMessage( "Success", "Your comment has been received. Comment approval is turned on for this post. Your comment will be reviewed shortly.");
                break;
            case AUTHOR_BANNED:
                resultMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", "Sorry, your email address is banned.");
                break;
            case ERROR:
                resultMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Your comment could not be posted. Please try again shortly and let us know if this persists.");
                break;
            default:
                resultMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "Internal system error.");
                break;
        }
        getCurrentFacesContext().addMessage("commentPostResult", resultMessage);
        resetNewComment();
    }

    public void approveComment(int commentId) {
        commentManager.approveComment(commentId);
    }

    private void resetNewComment() {
        newCommentAuthor = "";
        newCommentEmail = "";
        newCommentBody = "";
    }
}
