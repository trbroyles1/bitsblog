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
@Table(name = "post_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostStatus.findAll", query = "SELECT p FROM PostStatus p"),
    @NamedQuery(name = "PostStatus.findByPostStatusId", query = "SELECT p FROM PostStatus p WHERE p.postStatusId = :postStatusId"),
    @NamedQuery(name = "PostStatus.findByName", query = "SELECT p FROM PostStatus p WHERE p.name = :name"),
    @NamedQuery(name = "PostStatus.findByDateCreated", query = "SELECT p FROM PostStatus p WHERE p.dateCreated = :dateCreated")})
public class PostStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_status_id")
    private Integer postStatusId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postStatus")
    private List<Post> postList;

    public PostStatus() {
    }

    public PostStatus(Integer postStatusId) {
        this.postStatusId = postStatusId;
    }

    public PostStatus(Integer postStatusId, String name, Date dateCreated) {
        this.postStatusId = postStatusId;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Integer getPostStatusId() {
        return postStatusId;
    }

    public void setPostStatusId(Integer postStatusId) {
        this.postStatusId = postStatusId;
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
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postStatusId != null ? postStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostStatus)) {
            return false;
        }
        PostStatus other = (PostStatus) object;
        if ((this.postStatusId == null && other.postStatusId != null) || (this.postStatusId != null && !this.postStatusId.equals(other.postStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.PostStatus[ postStatusId=" + postStatusId + " ]";
    }
    
}
