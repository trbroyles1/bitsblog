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
@Table(name = "page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page.findAll", query = "SELECT p FROM Page p"),
    @NamedQuery(name = "Page.findByPageId", query = "SELECT p FROM Page p WHERE p.pageId = :pageId"),
    @NamedQuery(name = "Page.findByTitle", query = "SELECT p FROM Page p WHERE p.title = :title"),
    @NamedQuery(name = "Page.findByDateCreated", query = "SELECT p FROM Page p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "Page.findByDateLastModified", query = "SELECT p FROM Page p WHERE p.dateLastModified = :dateLastModified")})
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "page_id")
    private Integer pageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
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
    @JoinColumn(name = "page_status_id", referencedColumnName = "page_status_id")
    @ManyToOne(optional = false)
    private PageStatus pageStatusId;
    @JoinColumn(name = "page_author_id", referencedColumnName = "page_author_id")
    @ManyToOne(optional = false)
    private PageAuthor pageAuthorId;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private Category categoryId;

    public Page() {
    }

    public Page(Integer pageId) {
        this.pageId = pageId;
    }

    public Page(Integer pageId, String title, String body, Date dateCreated) {
        this.pageId = pageId;
        this.title = title;
        this.body = body;
        this.dateCreated = dateCreated;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
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

    public PageStatus getPageStatusId() {
        return pageStatusId;
    }

    public void setPageStatusId(PageStatus pageStatusId) {
        this.pageStatusId = pageStatusId;
    }

    public PageAuthor getPageAuthorId() {
        return pageAuthorId;
    }

    public void setPageAuthorId(PageAuthor pageAuthorId) {
        this.pageAuthorId = pageAuthorId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pageId != null ? pageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Page)) {
            return false;
        }
        Page other = (Page) object;
        if ((this.pageId == null && other.pageId != null) || (this.pageId != null && !this.pageId.equals(other.pageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.Page[ pageId=" + pageId + " ]";
    }
    
}
