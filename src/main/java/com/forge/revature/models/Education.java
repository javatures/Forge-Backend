package com.forge.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "id")
    private Portfolio portfolio;
    private String university;
    private String degree;
    private String graduationDate;
    private double gpa;
    private String logoUrl;
    //private Certification certifications

    public Education(String university, String degree, String graduationDate, double gpa, String logoUrl) {
        this.university = university;
        this.degree = degree;
        this.graduationDate = graduationDate;
        this.gpa = gpa;
        this.logoUrl = logoUrl;
    }

    public Education(String university, String degree, String graduationDate, double gpa) {
        this.university = university;
        this.degree = degree;
        this.graduationDate = graduationDate;
        this.gpa = gpa;
    }

    public Education(Portfolio portfolio, String university, String degree, String graduationDate, double gpa) {
        this.portfolio = portfolio;
        this.university = university;
        this.degree = degree;
        this.graduationDate = graduationDate;
        this.gpa = gpa;
    }
}
