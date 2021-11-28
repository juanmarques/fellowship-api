package com.fellowship.api.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@Builder
@Data
public class PostDTO implements Serializable {

    private String postId;
    private String text;
    private Long createdAt;
    private int postType;
    private String postLocalization;
    private String profilePic;
    private String name;
    private String tag;
    private String propertyType;
    private Long propertyPrice;
    private List<CommentDTO> comments;
    private List<MediaPostDTO> mediaPosts;
}

