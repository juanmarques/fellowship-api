package com.fellowship.api.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String text;
    private Long createdAt;
    private int postType;
    private String postLocalization;
    private String tags;
    private String propertyType;
    private Long propertyPrice;

    @ManyToOne
    @JoinColumn(name = "fellowshipUser_id", referencedColumnName = "id")
    private FellowshipUser fellowshipUser;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaPost> mediaPosts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cheer> cheers;
}
