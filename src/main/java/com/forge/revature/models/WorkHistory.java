package com.forge.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workhistory")
@NoArgsConstructor
@AllArgsConstructor
public class WorkHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String title;

  @Column
  private String employer;

  @Column
  private String responsibilities;

  @Column
  private String description;

  @Column 
  private String tools;

  @Column
  private String startDate;

  @Column
  private String endDate;

  @ManyToOne
  @JoinColumn
  private Portfolio portfolio;

  public WorkHistory(String title, String employer, String responsibilities, String description, String tools, String startDate, String endDate) {
    this.title = title;
    this.employer = employer;
    this.responsibilities = responsibilities;
    this.description = description;
    this.tools = tools;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getEmployer() {
    return employer;
  }

  public void setEmployer(String employer) {
    this.employer = employer;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getTools() {
    return tools;
  }

  public void setTools(String tools) {
    this.tools = tools;
  }

}