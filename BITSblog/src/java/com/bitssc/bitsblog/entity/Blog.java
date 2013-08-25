/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "blog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blog.findAll", query = "SELECT b FROM Blog b"),
    @NamedQuery(name = "Blog.findByBlogId", query = "SELECT b FROM Blog b WHERE b.blogId = :blogId"),
    @NamedQuery(name = "Blog.findByName", query = "SELECT b FROM Blog b WHERE b.name = :name"),
    @NamedQuery(name = "Blog.findByTagline", query = "SELECT b FROM Blog b WHERE b.tagline = :tagline"),
    @NamedQuery(name = "Blog.findByDateCreated", query = "SELECT b FROM Blog b WHERE b.dateCreated = :dateCreated"),
    @NamedQuery(name = "Blog.findByDateLastModified", query = "SELECT b FROM Blog b WHERE b.dateLastModified = :dateLastModified")})
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "blog_id")
    private Integer blogId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "name")
    private String name;
    @Size(max = 300)
    @Column(name = "tagline")
    private String tagline;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "dateLastModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastModified;

    public Blog() {
    }

    public Blog(Integer blogId) {
        this.blogId = blogId;
    }

    public Blog(Integer blogId, String name, Date dateCreated) {
        this.blogId = blogId;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogId != null ? blogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blog)) {
            return false;
        }
        Blog other = (Blog) object;
        if ((this.blogId == null && other.blogId != null) || (this.blogId != null && !this.blogId.equals(other.blogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.Blog[ blogId=" + blogId + " ]";
    }
    
}
