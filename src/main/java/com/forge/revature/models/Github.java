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
@Table(name = "github")
@Getter
@Setter
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

  //@OnetoOne
  // @JoinColumn
  // private User user;

  public GitHub(String url, String image) {
    this.url = url;
    this.image = image;
  }

}