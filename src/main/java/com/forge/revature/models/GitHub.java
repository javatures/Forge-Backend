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
@Table(name = "github")
@NoArgsConstructor
@AllArgsConstructor

public class GitHub{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique=true)
  private String url;

  @Column
  private String image;

  public GitHub(String url, String image) {
    this.url = url;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }



}