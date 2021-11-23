package com.fellowship.api.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@Getter
@Setter
@Builder
public class UserProfileDTO implements Serializable {

    private String userId;
    private String name;
    private String email;
    private String profilePic;
    private String birthdayDate;
    private String neighbourhood;
    private String postalCode;
    private String password;
    private String job;
    private String city;
    private String hobbies;
    private String contact;
    private String relationship;
    private String about;
}
