package com.fellowship.api.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 20/11/2021
 */
@Entity
@Getter
@Setter
public class Cheer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fellowship_user_id", referencedColumnName = "id")
    private FellowshipUser fellowshipUser;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    private boolean active;

    private Integer cheerType;
}
