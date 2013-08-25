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
@Table(name = "page_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PageStatus.findAll", query = "SELECT p FROM PageStatus p"),
    @NamedQuery(name = "PageStatus.findByPageStatusId", query = "SELECT p FROM PageStatus p WHERE p.pageStatusId = :pageStatusId"),
    @NamedQuery(name = "PageStatus.findByName", query = "SELECT p FROM PageStatus p WHERE p.name = :name"),
    @NamedQuery(name = "PageStatus.findByDateCreated", query = "SELECT p FROM PageStatus p WHERE p.dateCreated = :dateCreated")})
public class PageStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "page_status_id")
    private Integer pageStatusId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageStatusId")
    private List<Page> pageList;

    public PageStatus() {
    }

    public PageStatus(Integer pageStatusId) {
        this.pageStatusId = pageStatusId;
    }

    public PageStatus(Integer pageStatusId, String name, Date dateCreated) {
        this.pageStatusId = pageStatusId;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Integer getPageStatusId() {
        return pageStatusId;
    }

    public void setPageStatusId(Integer pageStatusId) {
        this.pageStatusId = pageStatusId;
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
    public List<Page> getPageList() {
        return pageList;
    }

    public void setPageList(List<Page> pageList) {
        this.pageList = pageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pageStatusId != null ? pageStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PageStatus)) {
            return false;
        }
        PageStatus other = (PageStatus) object;
        if ((this.pageStatusId == null && other.pageStatusId != null) || (this.pageStatusId != null && !this.pageStatusId.equals(other.pageStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bitssc.bitsblog.entity.PageStatus[ pageStatusId=" + pageStatusId + " ]";
    }
    
}
