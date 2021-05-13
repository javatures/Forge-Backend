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
@Table(name = "honors")
@Getter
@Setter
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

}