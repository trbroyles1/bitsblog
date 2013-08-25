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
@Table(name = "post_author")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostAuthor.findAll", query = "SELECT p FROM PostAuthor p"),
    @NamedQuery(name = "PostAuthor.findByPostAuthorId", query = "SELECT p FROM PostAuthor p WHERE p.postAuthorId = :postAuthorId"),
    @NamedQuery(name = "PostAuthor.findByUserName", query = "SELECT p FROM PostAuthor p WHERE p.userName = :userName"),
    @NamedQuery(name = "PostAuthor.findByFirstName", query = "SELECT p FROM PostAuthor p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "PostAuthor.findByLastName", query = "SELECT p FROM PostAuthor p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "PostAuthor.findByEmailAddress", query = "SELECT p FROM PostAuthor p WHERE p.emailAddress = :emailAddress"),
    @NamedQuery(name = "PostAuthor.findByActive", query = "SELECT p FROM PostAuthor p WHERE p.active = :active"),
    @NamedQuery(name = "PostAuthor.findByDateCreated", query = "SELECT p FROM PostAuthor p WHERE p.dateCreated = :dateCreated"),
    @NamedQuery(name = "PostAuthor.findByDateLastModified", query = "SELECT p FROM PostAuthor p WHERE p.dateLastModified = :dateLastModified")})
public class PostAuthor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_author_id")
    private Integer postAuthorId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postAuthor")
    private List<Post> postList;

    public PostAuthor() {
    }

    public PostAuthor(Integer postAuthorId) {
        this.postAuthorId = postAuthorId;
    }

    public PostAuthor(Integer postAuthorId, String userName, String firstName, String lastName, String emailAddress, boolean active, Date dateCreated) {
        this.postAuthorId = postAuthorId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.active = active;
        this.dateCreated = dateCreated;
    }

    public Integer getPostAuthorId() {
        return postAuthorId;
    }

    public void setPostAuthorId(Integer postAuthorId) {
        this.postAuthorId = postAuthorId;
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
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postAuthorId != null ? postAuthorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostAuthor)) {
            return false;
        }
        PostAuthor other = (PostAuthor) object;
        if ((this.postAuthorId == null && other.postAuthorId != null) || (this.postAuthorId != null && !this.postAuthorId.equals(other.postAuthorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.PostAuthor[ postAuthorId=" + postAuthorId + " ]";
    }
    
}
