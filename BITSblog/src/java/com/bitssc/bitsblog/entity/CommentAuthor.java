/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RBroyles
 */
@Entity
@Table(name = "comment_author")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentAuthor.findAll", query = "SELECT c FROM CommentAuthor c"),
    @NamedQuery(name = "CommentAuthor.findByCommentAuthorId", query = "SELECT c FROM CommentAuthor c WHERE c.commentAuthorId = :commentAuthorId"),
    @NamedQuery(name = "CommentAuthor.findByName", query = "SELECT c FROM CommentAuthor c WHERE c.name = :name"),
    @NamedQuery(name = "CommentAuthor.findByEmailAddress", query = "SELECT c FROM CommentAuthor c WHERE c.emailAddress = :emailAddress"),
    @NamedQuery(name = "CommentAuthor.findByDateCreated", query = "SELECT c FROM CommentAuthor c WHERE c.dateCreated = :dateCreated")})
public class CommentAuthor implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentApprovalRequired")
    private boolean commentApprovalRequired;
    @Basic(optional = false)
    @NotNull
    @Column(name = "banned")
    private boolean banned;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_author_id")
    private Integer commentAuthorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "emailAddress")
    private String emailAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentAuthor")
    private List<Comment> commentList;

    public CommentAuthor() {
    }

    public CommentAuthor(Integer commentAuthorId) {
        this.commentAuthorId = commentAuthorId;
    }

    public CommentAuthor(Integer commentAuthorId, String name, String emailAddress, Date dateCreated) {
        this.commentAuthorId = commentAuthorId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.dateCreated = dateCreated;
    }

    public Integer getCommentAuthorId() {
        return commentAuthorId;
    }

    public void setCommentAuthorId(Integer commentAuthorId) {
        this.commentAuthorId = commentAuthorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
    
    public boolean getCommentApprovalRequired() {
        return commentApprovalRequired;
    }

    public void setCommentApprovalRequired(boolean commentApprovalRequired) {
        this.commentApprovalRequired = commentApprovalRequired;
    }

    public boolean getBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentAuthorId != null ? commentAuthorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentAuthor)) {
            return false;
        }
        CommentAuthor other = (CommentAuthor) object;
        if ((this.commentAuthorId == null && other.commentAuthorId != null) || (this.commentAuthorId != null && !this.commentAuthorId.equals(other.commentAuthorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.CommentAuthor[ commentAuthorId=" + commentAuthorId + " ]";
    }
    
}
