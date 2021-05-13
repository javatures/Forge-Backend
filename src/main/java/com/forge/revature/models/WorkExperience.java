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
@Table(name = "workexperience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workexperienceid")
    private long id;

    @Column
    private String employer;

    @Column
    private String title;

    @Column
    private String responsibilities;

    @Column
    private String description;

    @Column
    private String technologies;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public WorkExperience() {
    }

    public WorkExperience(String employer, String title, String responsibilities, String description,
            String technologies, Date startDate, Date endDate) {
        this.employer = employer;
        this.title = title;
        this.responsibilities = responsibilities;
        this.description = description;
        this.technologies = technologies;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WorkExperience [description=" + description + ", employer=" + employer + ", endDate=" + endDate
                + ", id=" + id + ", responsibilities=" + responsibilities + ", startDate=" + startDate
                + ", technologies=" + technologies + ", title=" + title + "]";
    }

    
}
