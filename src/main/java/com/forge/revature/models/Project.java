package com.forge.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projectid")
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(length = 10000)
    private String responsibilities;

    @Column(length = 10000)
    private String technologies;

    @Column
    private String respositoryUrl;

    @Column
    private String workProducts;

    @OneToOne
    @JoinColumn(name = "id")
    private Portfolio portfolio;

    public Project() {
    }

    public Project(String name, String description, String responsibilities, String technologies, String respositoryUrl,
            Portfolio portfolio) {
        this.name = name;
        this.description = description;
        this.responsibilities = responsibilities;
        this.technologies = technologies;
        this.respositoryUrl = respositoryUrl;
        this.portfolio = portfolio;
    }

    public Project(long id, String name, String description, String responsibilities, String technologies,
            String respositoryUrl, String workProducts, Portfolio portfolio) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.responsibilities = responsibilities;
        this.technologies = technologies;
        this.respositoryUrl = respositoryUrl;
        this.workProducts = workProducts;
        this.portfolio = portfolio;
    }

    public Project(String name, String description, String responsibilities, String technologies,
            String respositoryUrl) {
        this.name = name;
        this.description = description;
        this.responsibilities = responsibilities;
        this.technologies = technologies;
        this.respositoryUrl = respositoryUrl;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getRespositoryUrl() {
        return respositoryUrl;
    }

    public void setRespositoryUrl(String respositoryUrl) {
        this.respositoryUrl = respositoryUrl;
    }

    public String getWorkProducts() {
        return workProducts;
    }

    public void setWorkProducts(String workProducts) {
        this.workProducts = workProducts;
    }

    @Override
    public String toString() {
        return "Project [description=" + description + ", id=" + id + ", name=" + name + ", portfolio=" + portfolio
                + ", responsibilities=" + responsibilities + ", respositoryUrl=" + respositoryUrl + ", technologies="
                + technologies + ", workProducts=" + workProducts + "]";
    }
}
