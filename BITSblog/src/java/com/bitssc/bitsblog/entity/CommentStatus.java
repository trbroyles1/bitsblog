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
@Table(name = "comment_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentStatus.findAll", query = "SELECT c FROM CommentStatus c"),
    @NamedQuery(name = "CommentStatus.findByCommentStatusId", query = "SELECT c FROM CommentStatus c WHERE c.commentStatusId = :commentStatusId"),
    @NamedQuery(name = "CommentStatus.findByName", query = "SELECT c FROM CommentStatus c WHERE c.name = :name"),
    @NamedQuery(name = "CommentStatus.findByDateCreated", query = "SELECT c FROM CommentStatus c WHERE c.dateCreated = :dateCreated")})
public class CommentStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_status_id")
    private Integer commentStatusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentStatus")
    private List<Comment> commentList;

    public CommentStatus() {
    }

    public CommentStatus(Integer commentStatusId) {
        this.commentStatusId = commentStatusId;
    }

    public CommentStatus(Integer commentStatusId, String name, Date dateCreated) {
        this.commentStatusId = commentStatusId;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Integer getCommentStatusId() {
        return commentStatusId;
    }

    public void setCommentStatusId(Integer commentStatusId) {
        this.commentStatusId = commentStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentStatusId != null ? commentStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentStatus)) {
            return false;
        }
        CommentStatus other = (CommentStatus) object;
        if ((this.commentStatusId == null && other.commentStatusId != null) || (this.commentStatusId != null && !this.commentStatusId.equals(other.commentStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.CommentStatus[ commentStatusId=" + commentStatusId + " ]";
    }
    
}
