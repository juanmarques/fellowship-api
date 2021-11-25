package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CommentDTO;
import com.fellowship.api.domain.dtos.PostDTO;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;

/**
 * @author Created by Juan Marques on 24/11/2021
 */

public interface PostService {

    PostDTO createPost(PostDTO postDTO, @CurrentUser UserPrincipal currentUser);
    void deletePost (PostDTO postDTO);
    void reportPost (PostDTO postDTO);
    CommentDTO addComment (CommentDTO commentDTO);
    void deleteComment(CommentDTO commentDTO);
    void reportComment (CommentDTO commentDTO);
}
