package com.fellowship.api.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 20/11/2021
 */
@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    private UUID id;
    private String text;
    private Long createdAt;
    private String mediaUrl;
    private String mediaType;
    private String postType;
    @ManyToOne
    @JoinColumn(name = "fellowship_user_id")
    private FellowshipUser fellowshipUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Cheer> cheers;
}
