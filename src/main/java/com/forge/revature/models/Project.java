package com.forge.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column
    private String responsibilities;

    @Column
    private int hours;

    @Column
    private int duration;

    public Project() {
    }

    public Project(String name, String description, String responsibilities, int hours, int duration) {
        this.name = name;
        this.description = description;
        this.responsibilities = responsibilities;
        this.hours = hours;
        this.duration = duration;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Project [description=" + description + ", duration=" + duration + ", hours=" + hours + ", id=" + id
                + ", name=" + name + ", responsibilities=" + responsibilities + "]";
    }
}
