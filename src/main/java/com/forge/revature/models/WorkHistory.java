package com.forge.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workhistory")
@Getter
@Setter
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

  public WorkHistory(String title, String responsibilities, String description, String tools, String date) {
    this.title = title;
    this.responsibilities = responsibilities;
    this.description = description;
    this.tools = tools;
    this.date = date;
  }

}