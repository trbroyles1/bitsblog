/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.backing;

import com.bitssc.bitsblog.entity.CommentAuthor;
import com.bitssc.bitsblog.facade.CommentAuthorFacade;
import com.bitssc.bitsblog.session.CommentManager;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author RBroyles
 */
@Named(value = "commentAuthorsBacker")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(
            id = "commentAuthors",
            pattern = "/admin/commentAuthors",
            viewId = "/faces/admin/commentAuthors.xhtml"
        )
})
public class CommentAuthorsBacker {
    @EJB
    private CommentManager commentManager;
    @EJB
    private CommentAuthorFacade commentAuthorFacade;
    
    

    /**
     * Creates a new instance of CommentAuthorsBacker
     */
    public CommentAuthorsBacker() {
    }
    
    public List<CommentAuthor> getAllCommentAuthors(){
        return commentAuthorFacade.findAll();
    }
    
    public void banCommentAuthor(int commentAuthorId){
        commentManager.banCommentAuthor(commentAuthorId);
    }
    
    public void unBanCommentAuthor(int commentAuthorId){
        CommentAuthor author = commentAuthorFacade.find(commentAuthorId);
        author.setBanned(false);
        commentAuthorFacade.edit(author);
    }
    
    public void turnOnCommentApproval(int commentAuthorId){
        CommentAuthor author = commentAuthorFacade.find(commentAuthorId);
        author.setCommentApprovalRequired(true);
        commentAuthorFacade.edit(author);
    }
    
    public void turnOffCommentApproval(int commentAuthorId){
        CommentAuthor author = commentAuthorFacade.find(commentAuthorId);
        author.setCommentApprovalRequired(false);
        commentAuthorFacade.edit(author);
    }
}
