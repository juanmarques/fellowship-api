package com.fellowship.api.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@Data
@Builder
public class CommentDTO implements Serializable {

    private String commentId;
    private String postId;
    private String userId;
    private String userPic;
    private String name;
    private String text;
    private Long createdAt;
}
