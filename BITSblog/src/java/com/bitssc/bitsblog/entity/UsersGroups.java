/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ryan
 */
@Entity
@Table(name = "users_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersGroups.findAll", query = "SELECT u FROM UsersGroups u"),
    @NamedQuery(name = "UsersGroups.findByGroupName", query = "SELECT u FROM UsersGroups u WHERE u.usersGroupsPK.groupName = :groupName"),
    @NamedQuery(name = "UsersGroups.findByUserName", query = "SELECT u FROM UsersGroups u WHERE u.usersGroupsPK.userName = :userName")})
public class UsersGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersGroupsPK usersGroupsPK;

    public UsersGroups() {
    }

    public UsersGroups(UsersGroupsPK usersGroupsPK) {
        this.usersGroupsPK = usersGroupsPK;
    }

    public UsersGroups(String groupName, String userName) {
        this.usersGroupsPK = new UsersGroupsPK(groupName, userName);
    }

    public UsersGroupsPK getUsersGroupsPK() {
        return usersGroupsPK;
    }

    public void setUsersGroupsPK(UsersGroupsPK usersGroupsPK) {
        this.usersGroupsPK = usersGroupsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersGroupsPK != null ? usersGroupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersGroups)) {
            return false;
        }
        UsersGroups other = (UsersGroups) object;
        if ((this.usersGroupsPK == null && other.usersGroupsPK != null) || (this.usersGroupsPK != null && !this.usersGroupsPK.equals(other.usersGroupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.UsersGroups[ usersGroupsPK=" + usersGroupsPK + " ]";
    }
    
}
