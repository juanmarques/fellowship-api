package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CommentDTO;
import com.fellowship.api.domain.dtos.MediaPostDTO;
import com.fellowship.api.domain.dtos.PostDTO;
import com.fellowship.api.domain.entities.MediaPost;
import com.fellowship.api.domain.entities.Post;
import com.fellowship.api.repositories.CommentRepository;
import com.fellowship.api.repositories.PostRepository;
import com.fellowship.api.repositories.UserRepository;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, @CurrentUser UserPrincipal currentUser) {
        Post newPost = new Post();

        newPost.setCreatedAt(postDTO.getCreatedAt());
        newPost.setText(postDTO.getText());
        newPost.setMediaPosts(postDTO.getMediaPosts()
                .stream().map(dto -> {
                    MediaPost newMedia = new MediaPost();
                    newMedia.setMediaUrl(dto.getMediaUrl());
                    newMedia.setMediaType(dto.getMediaType());
                    newMedia.setPost(newPost);
                    return newMedia;
                }).collect(Collectors.toList()));
        newPost.setPostType(postDTO.getPostType());
        newPost.setPostLocalization(postDTO.getPostLocalization());
        newPost.setFellowshipUser(userRepository.findById(UUID.fromString(currentUser.getId())).orElseThrow());

        return buildPostDTO(postRepository.save(newPost));
    }

    @Override
    public void deletePost(PostDTO postDTO) {
        this.postRepository.deleteById(postDTO.getPostId());
    }

    @Override
    public void reportPost(PostDTO postDTO) {

    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        return null;
    }

    @Override
    public void deleteComment(CommentDTO commentDTO) {
        this.commentRepository.deleteById(commentDTO.getCommentId());
    }

    @Override
    public void reportComment(CommentDTO commentDTO) {

    }

    private PostDTO buildPostDTO(Post post) {

        PostDTO.PostDTOBuilder postDTOBuilder = PostDTO.builder();

        postDTOBuilder
                .postId(String.valueOf(post.getId()))
                .postType(post.getPostType())
                .postLocalization(post.getPostLocalization())
                .text(post.getText())
                .createdAt(post.getCreatedAt())
                .name(post.getFellowshipUser().getName())
                .profilePic(post.getFellowshipUser().getProfilePic());

        if (post.getMediaPosts() != null) {
            postDTOBuilder.mediaPosts(post.getMediaPosts().stream().map(dbMedia -> MediaPostDTO.builder().mediaType(dbMedia.getMediaType())
                    .mediaUrl(dbMedia.getMediaUrl()).build()).collect(Collectors.toList()));

            if (post.getComments() != null) {
                postDTOBuilder.comments(post.getComments().stream().map(dbComments -> CommentDTO.builder()
                        .commentId(String.valueOf(dbComments.getId()))
                        .createdAt(dbComments.getCreated())
                        .text(dbComments.getText())
                        .postId(String.valueOf(post.getId()))
                        .userId(String.valueOf(dbComments.getFellowshipUser().getId())).build())
                        .collect(Collectors.toList()));
            }
        }
        return postDTOBuilder.build();
    }
}