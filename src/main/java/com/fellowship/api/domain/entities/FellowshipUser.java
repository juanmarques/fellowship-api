package com.fellowship.api.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 20/11/2021
 */

@Entity
@Getter
@Setter
public class FellowshipUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private boolean active;
    private String birthdayDate;
    private String profilePic;
    private String postalCode;
    private String neighbourhood;
    private String job;
    private String city;
    private String hobbies;
    private String contact;
    private String relationship;
    private String about;
    @OneToMany(mappedBy = "fellowshipUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post;
    @OneToMany(mappedBy = "fellowshipUser",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cheer> cheers;
}
