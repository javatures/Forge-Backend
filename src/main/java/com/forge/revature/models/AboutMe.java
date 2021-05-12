package com.forge.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class AboutMe {
    @Id
    @GeneratedValue
    private int id;
    //@OneToOne
    //private Portfolio portfolio;
    private String bio;
    private String email;
    private String phone;

    public AboutMe(String bio, String email, String phone) {
        this.bio = bio;
        this.email = email;
        this.phone = phone;
    }
}
