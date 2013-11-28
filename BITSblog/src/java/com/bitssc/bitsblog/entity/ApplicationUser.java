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
 * @author ryan
 */
@Entity
@Table(name = "applicationUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationUser.findAll", query = "SELECT a FROM ApplicationUser a"),
    @NamedQuery(name = "ApplicationUser.findByUserId", query = "SELECT a FROM ApplicationUser a WHERE a.userId = :userId"),
    @NamedQuery(name = "ApplicationUser.findByUserName", query = "SELECT a FROM ApplicationUser a WHERE a.userName = :userName"),
    @NamedQuery(name = "ApplicationUser.findByUserSecret", query = "SELECT a FROM ApplicationUser a WHERE a.userSecret = :userSecret"),
    @NamedQuery(name = "ApplicationUser.findByDateCreated", query = "SELECT a FROM ApplicationUser a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "ApplicationUser.findByDateLastModified", query = "SELECT a FROM ApplicationUser a WHERE a.dateLastModified = :dateLastModified")})
public class ApplicationUser implements Serializable {
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

    public ApplicationUser() {
    }

    public ApplicationUser(Integer userId) {
        this.userId = userId;
    }

    public ApplicationUser(Integer userId, String userName, String userSecret, Date dateCreated, Date dateLastModified) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationUser)) {
            return false;
        }
        ApplicationUser other = (ApplicationUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.ApplicationUser[ userId=" + userId + " ]";
    }
    
}
