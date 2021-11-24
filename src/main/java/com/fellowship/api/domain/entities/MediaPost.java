package com.fellowship.api.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@Entity
@Getter
@Setter
public class MediaPost {

    @Id
    @GeneratedValue
    private UUID id;
    private String mediaUrl;
    private String mediaType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
