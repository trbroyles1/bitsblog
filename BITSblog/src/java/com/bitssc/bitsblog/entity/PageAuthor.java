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
@Table(name = "page_author")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PageAuthor.findAll", query = "SELECT p FROM PageAuthor p"),
    @NamedQuery(name = "PageAuthor.findByPageAuthorId", query = "SELECT p FROM PageAuthor p WHERE p.pageAuthorId = :pageAuthorId"),
    @NamedQuery(name = "PageAuthor.findByUserName", query = "SELECT p FROM PageAuthor p WHERE p.userName = :userName"),
    @NamedQuery(name = "PageAuthor.findByFirstName", query = "SELECT p FROM PageAuthor p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "PageAuthor.findByLastName", query = "SELECT p FROM PageAuthor p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "PageAuthor.findByEmailAddress", query = "SELECT p FROM PageAuthor p WHERE p.emailAddress = :emailAddress"),
    @NamedQuery(name = "PageAuthor.findByActive", query = "SELECT p FROM PageAuthor p WHERE p.active = :active"),
    @NamedQuery(name = "PageAuthor.findByDateCreated", query = "SELECT p FROM PageAuthor p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "PageAuthor.findByDateLastModified", query = "SELECT p FROM PageAuthor p WHERE p.dateLastModified = :dateLastModified")})
public class PageAuthor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "page_author_id")
    private Integer pageAuthorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "emailAddress")
    private String emailAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "dateLastModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastModified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageAuthorId")
    private List<Page> pageList;

    public PageAuthor() {
    }

    public PageAuthor(Integer pageAuthorId) {
        this.pageAuthorId = pageAuthorId;
    }

    public PageAuthor(Integer pageAuthorId, String userName, String firstName, String lastName, String emailAddress, boolean active, Date dateCreated) {
        this.pageAuthorId = pageAuthorId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.active = active;
        this.dateCreated = dateCreated;
    }

    public Integer getPageAuthorId() {
        return pageAuthorId;
    }

    public void setPageAuthorId(Integer pageAuthorId) {
        this.pageAuthorId = pageAuthorId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    @XmlTransient
    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pageAuthorId != null ? pageAuthorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PageAuthor)) {
            return false;
        }
        PageAuthor other = (PageAuthor) object;
        if ((this.pageAuthorId == null && other.pageAuthorId != null) || (this.pageAuthorId != null && !this.pageAuthorId.equals(other.pageAuthorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.PageAuthor[ pageAuthorId=" + pageAuthorId + " ]";
    }
    
}
