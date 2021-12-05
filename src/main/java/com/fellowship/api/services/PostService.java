package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.PostDTO;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;

import java.util.List;

/**
 * @author Created by Juan Marques on 24/11/2021
 */

public interface PostService {

    PostDTO createPost(PostDTO postDTO, @CurrentUser UserPrincipal currentUser);
    void deletePost (String postId);
    void reportPost (PostDTO postDTO);
    List<PostDTO> getPostByType(int postType,@CurrentUser UserPrincipal currentUser);
}
