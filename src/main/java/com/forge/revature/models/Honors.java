package com.forge.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "honors")
@NoArgsConstructor
@AllArgsConstructor

public class Honors {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String title;

  @Column
  private String description;

  @Column 
  private String dateReceived;

  @Column
  private String receivedFrom;

  public Honors(String title, String description, String dateReceived, String receivedFrom) {
    this.title = title;
    this.description = description;
    this.dateReceived = dateReceived;
    this.receivedFrom = receivedFrom;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDateReceived() {
    return dateReceived;
  }

  public void setDateReceived(String dateReceived) {
    this.dateReceived = dateReceived;
  }

  public String getReceivedFrom() {
    return receivedFrom;
  }

  public void setReceivedFrom(String receivedFrom) {
    this.receivedFrom = receivedFrom;
  }
  

}