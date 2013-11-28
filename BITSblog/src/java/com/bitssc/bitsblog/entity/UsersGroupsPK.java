/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitssc.bitsblog.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ryan
 */
@Embeddable
public class UsersGroupsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "groupName")
    private String groupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "userName")
    private String userName;

    public UsersGroupsPK() {
    }

    public UsersGroupsPK(String groupName, String userName) {
        this.groupName = groupName;
        this.userName = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupName != null ? groupName.hashCode() : 0);
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersGroupsPK)) {
            return false;
        }
        UsersGroupsPK other = (UsersGroupsPK) object;
        if ((this.groupName == null && other.groupName != null) || (this.groupName != null && !this.groupName.equals(other.groupName))) {
            return false;
        }
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.UsersGroupsPK[ groupName=" + groupName + ", userName=" + userName + " ]";
    }
    
}
