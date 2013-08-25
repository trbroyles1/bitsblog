/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RBroyles
 */

@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCommentId", query = "SELECT c FROM Comment c WHERE c.commentId = :commentId"),
    @NamedQuery(name = "Comment.findByPostId", query = "SELECT c FROM Comment c WHERE c.postId.postId = :postId"),
    @NamedQuery(name = "Comment.findByDateCreated", query = "SELECT c FROM Comment c WHERE c.dateCreated = :dateCreated"),
    @NamedQuery(name = "Comment.findByStatus", query = "SELECT c FROM Comment c WHERE c.commentStatus.name = :commentStatusName"),
    @NamedQuery(name = "Comment.findByStatusNameAndPostId", query = "SELECT c FROM Comment c WHERE c.commentStatus.name = :commentStatusName AND c.postId.postId = :postId" )})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_id")
    private Integer commentId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "body")
    private String body;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @ManyToOne(optional = false)
    private Post postId;
    @JoinColumn(name = "comment_status", referencedColumnName = "comment_status_id")
    @ManyToOne(optional = false)
    private CommentStatus commentStatus;
    @JoinColumn(name = "comment_author", referencedColumnName = "comment_author_id")
    @ManyToOne(optional = false)
    private CommentAuthor commentAuthor;
    
    public static String STATUS_APPROVED = "approved";
    public static String STATUS_PENDING = "pending";
    public static String STATUS_CENSORED = "censored";

    public Comment() {
    }

    public Comment(Integer commentId) {
        this.commentId = commentId;
    }

    public Comment(Integer commentId, String body, Date dateCreated) {
        this.commentId = commentId;
        this.body = body;
        this.dateCreated = dateCreated;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public CommentAuthor getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(CommentAuthor commentAuthor) {
        this.commentAuthor = commentAuthor;
    }
    
    public String getFriendlyPostTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy @ hh:mm aaa");
        return formatter.format(dateCreated).toLowerCase();
    }
    
    public boolean getIsApproved(){
        return this.commentStatus.getName().equals(STATUS_APPROVED);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.Comment[ commentId=" + commentId + " ]";
    }
    
}
