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
  private String responsibilities;

  @Column
  private String description;

  @Column 
  private String tools;

  @Column
  private String date;

  @ManyToOne
  @JoinColumn
  private Portfolio portfolio;

  public WorkHistory(String title, String responsibilities, String description, String tools, String date) {
    this.title = title;
    this.responsibilities = responsibilities;
    this.description = description;
    this.tools = tools;
    this.date = date;
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}