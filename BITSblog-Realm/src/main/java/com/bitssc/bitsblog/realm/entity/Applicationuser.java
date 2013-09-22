/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.realm.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "applicationUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applicationuser.findAll", query = "SELECT a FROM Applicationuser a"),
    @NamedQuery(name = "Applicationuser.findByUserId", query = "SELECT a FROM Applicationuser a WHERE a.userId = :userId"),
    @NamedQuery(name = "Applicationuser.findByUserName", query = "SELECT a FROM Applicationuser a WHERE a.userName = :userName"),
    @NamedQuery(name = "Applicationuser.findByUserSecret", query = "SELECT a FROM Applicationuser a WHERE a.userSecret = :userSecret"),
    @NamedQuery(name = "Applicationuser.findByUserNameAndSecret", query = "SELECT u FROM Applicationuser u WHERE u.userName = :userName AND u.userSecret = :userSecret"),
    @NamedQuery(name = "Applicationuser.findByDateCreated", query = "SELECT a FROM Applicationuser a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "Applicationuser.findByDateLastModified", query = "SELECT a FROM Applicationuser a WHERE a.dateLastModified = :dateLastModified")})
public class Applicationuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "userSecret")
    private String userSecret;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateLastModified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastModified;
    @JoinTable(name = "user_group", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "group_id")})
    @ManyToMany
    private Collection<UserGroup> userGroupCollection;

    public Applicationuser() {
    }

    public Applicationuser(Integer userId) {
        this.userId = userId;
    }

    public Applicationuser(Integer userId, String userName, String userSecret, Date dateCreated, Date dateLastModified) {
        this.userId = userId;
        this.userName = userName;
        this.userSecret = userSecret;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
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
    public Collection<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(Collection<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicationuser)) {
            return false;
        }
        Applicationuser other = (Applicationuser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.realm.entity.Applicationuser[ userId=" + userId + " ]";
    }
    
}
