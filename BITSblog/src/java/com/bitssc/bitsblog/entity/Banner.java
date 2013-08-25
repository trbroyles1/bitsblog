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
import javax.persistence.Lob;
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
@Table(name = "banner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banner.findAll", query = "SELECT b FROM Banner b"),
    @NamedQuery(name = "Banner.findByBannerId", query = "SELECT b FROM Banner b WHERE b.bannerId = :bannerId"),
    @NamedQuery(name = "Banner.findByTitle", query = "SELECT b FROM Banner b WHERE b.title = :title"),
    @NamedQuery(name = "Banner.findByDisplayUntil", query = "SELECT b FROM Banner b WHERE b.displayUntil = :displayUntil")})
public class Banner implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "banner_id")
    private Integer bannerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "body")
    private String body;
    @Column(name = "displayUntil")
    @Temporal(TemporalType.TIMESTAMP)
    private Date displayUntil;

    public Banner() {
    }

    public Banner(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Banner(Integer bannerId, String title, String body) {
        this.bannerId = bannerId;
        this.title = title;
        this.body = body;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
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

    public Date getDisplayUntil() {
        return displayUntil;
    }

    public void setDisplayUntil(Date displayUntil) {
        this.displayUntil = displayUntil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bannerId != null ? bannerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banner)) {
            return false;
        }
        Banner other = (Banner) object;
        if ((this.bannerId == null && other.bannerId != null) || (this.bannerId != null && !this.bannerId.equals(other.bannerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.Banner[ bannerId=" + bannerId + " ]";
    }
    
}
