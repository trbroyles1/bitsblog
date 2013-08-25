/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByPostId", query = "SELECT p FROM Post p WHERE p.postId = :postId"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findByDateCreated", query = "SELECT p FROM Post p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "Post.findByDateLastModified", query = "SELECT p FROM Post p WHERE p.dateLastModified = :dateLastModified"),
    @NamedQuery(name = "Post.findByPinned", query = "SELECT p FROM Post p WHERE p.pinned = :pinned"),
    @NamedQuery(name = "Post.findAllDescending", query = "SELECT p FROM Post p ORDER BY p.postId DESC"),
    @NamedQuery(name = "Post.findByStatusDescending", query = "SELECT p FROM Post p WHERE p.postStatus.name = :statusName ORDER BY p.postId DESC"),
    @NamedQuery(name = "Post.findOlderThanByStatusDescending", query = "SELECT p FROM Post p WHERE p.postId < :postId AND p.postStatus.name = :statusName ORDER BY p.postId DESC"),
    @NamedQuery(name = "Post.findByYearMonthDayTitle", query = "SELECT p FROM Post p WHERE p.dateCreated >= :targetDate AND p.dateCreated < :followingDate AND p.title = :title"),
    @NamedQuery(name = "Post.findNewerThanByStatusAscending", query = "SELECT p FROM Post p WHERE p.postId > :postId AND p.postStatus.name = :statusName ORDER BY p.postId ASC")
})
public class Post implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "commentApprovalRequired")
    private boolean commentApprovalRequired;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lockedForComments")
    private boolean lockedForComments;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_id")
    private Integer postId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "title")
    private String title;
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
    @Column(name = "dateLastModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pinned")
    private boolean pinned;
    @JoinTable(name = "post_tag", joinColumns = {
        @JoinColumn(name = "post_id", referencedColumnName = "post_id")}, inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    @ManyToMany
    private List<Tag> tagList;
    @JoinColumn(name = "post_author", referencedColumnName = "post_author_id")
    @ManyToOne(optional = false)
    private PostAuthor postAuthor;
    @JoinColumn(name = "post_status", referencedColumnName = "post_status_id")
    @ManyToOne(optional = false)
    private PostStatus postStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private List<Comment> commentList;
    
    public static String STATUS_PUBLISHED = "published";

    public Post() {
    }

    public Post(Integer postId) {
        this.postId = postId;
    }

    public Post(Integer postId, String title, String body, Date dateCreated, boolean pinned) {
        this.postId = postId;
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
        this.pinned = pinned;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public boolean getPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public PostAuthor getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(PostAuthor postAuthor) {
        this.postAuthor = postAuthor;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
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

    public boolean getLockedForComments() {
        return lockedForComments;
    }

    public void setLockedForComments(boolean lockedForComments) {
        this.lockedForComments = lockedForComments;
    }
    
    public String getFriendlyPostTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy @ hh:mm aaa");
        return formatter.format(dateCreated).toLowerCase();
    }
    
    public String getPostDateAsString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(dateCreated);
    }
    
    public String getLinkableTitle(){
        return title.replaceAll(" ", "-").toLowerCase();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.Post[ postId=" + postId + " ]";
    }
    
}
