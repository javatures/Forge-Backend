package com.forge.revature.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "certification")
public class Certifcation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "certificationid")
    private long id;

    @Column
    private String name;

    @Column
    private String certificationId;

    @Column
    private String issuedBy;

    @Temporal(TemporalType.DATE)
    private Date issuedOn;

    @Column
    private String publicUrl;

    // @Column
    // private imageOfCert;

    public Certifcation(){

    }

    /**
     * @param name
     * @param certificationId
     * @param issuedBy
     * @param issuedOn
     * @param publicUrl
     */
    public Certifcation(String name, String certificationId, String issuedBy, Date issuedOn, String publicUrl) {
        this.name = name;
        this.certificationId = certificationId;
        this.issuedBy = issuedBy;
        this.issuedOn = issuedOn;
        this.publicUrl = publicUrl;
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
    public String getCertificationId() {
        return certificationId;
    }

    /**
     * @param certificationId the certificationId to set
     */
    public void setCertificationId(String certificationId) {
        this.certificationId = certificationId;
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
