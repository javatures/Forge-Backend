package com.forge.revature.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "certificationid")
    private long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column
    private String name;

    @Column
    private String certId;

    @Column
    private String issuedBy;

    @Temporal(TemporalType.DATE)
    private Date issuedOn;

    @Column
    private String publicUrl;

    // @Column
    // private imageOfCert;

    public Certification(){

    }

    /**
     * @param name
     * @param certId
     * @param issuedBy
     * @param issuedOn
     * @param publicUrl
     */
    public Certification(String name, String certId, String issuedBy, Date issuedOn, String publicUrl) {
        this.name = name;
        this.certId = certId;
        this.issuedBy = issuedBy;
        this.issuedOn = issuedOn;
        this.publicUrl = publicUrl;
    }
    
    /**
     * @param portfolio
     * @param name
     * @param certId
     * @param issuedBy
     * @param issuedOn
     * @param publicUrl
     */
    public Certification(Portfolio portfolio, String name, String certId, String issuedBy, Date issuedOn, String publicUrl) {
        this.portfolio = portfolio;
        this.name = name;
        this.certId = certId;
        this.issuedBy = issuedBy;
        this.issuedOn = issuedOn;
        this.publicUrl = publicUrl;
    }

    /**
     * @return the portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * @param portfolio the portfolio to set
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the certificationId
     */
    public String getCertId() {
        return certId;
    }

    /**
     * @param certificationId the certificationId to set
     */
    public void setCertId(String certId) {
        this.certId = certId;
    }

    /**
     * @return the issuedBy
     */
    public String getIssuedBy() {
        return issuedBy;
    }

    /**
     * @param issuedBy the issuedBy to set
     */
    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    /**
     * @return the issuedOn
     */
    public Date getIssuedOn() {
        return issuedOn;
    }

    /**
     * @param issuedOn the issuedOn to set
     */
    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    /**
     * @return the publicUrl
     */
    public String getPublicUrl() {
        return publicUrl;
    }

    /**
     * @param publicUrl the publicUrl to set
     */
    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }
    
}
