package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CommentDTO;

/**
 * @author Created by Juan Marques on 04/12/2021
 */
public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO);

    void deleteComment(String commentId);

    void reportComment(CommentDTO commentDTO);

}
