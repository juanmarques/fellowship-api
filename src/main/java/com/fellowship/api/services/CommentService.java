package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CommentDTO;

/**
 * @author Created by Juan Marques on 04/12/2021
 */
public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO);

    void deleteComment(CommentDTO commentDTO);

    void reportComment(CommentDTO commentDTO);

}
