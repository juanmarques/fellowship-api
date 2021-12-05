package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CheerDTO;
import com.fellowship.api.domain.dtos.CommentDTO;
import com.fellowship.api.domain.dtos.MediaPostDTO;
import com.fellowship.api.domain.dtos.PostDTO;
import com.fellowship.api.domain.entities.Cheer;
import com.fellowship.api.domain.entities.MediaPost;
import com.fellowship.api.domain.entities.Post;
import com.fellowship.api.repositories.CheerRepository;
import com.fellowship.api.repositories.PostRepository;
import com.fellowship.api.repositories.UserRepository;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService, CheerService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CheerRepository cheerRepository;

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
        newPost.setPropertyPrice(postDTO.getPropertyPrice());
        newPost.setTag(postDTO.getTag());
        newPost.setPropertyType(postDTO.getPropertyType());
        newPost.setFellowshipUser(userRepository.findById(UUID.fromString(currentUser.getId())).orElseThrow());

        return buildPostDTO(postRepository.save(newPost));
    }

    @Override
    public void deletePost(String postId) {
        this.postRepository.deleteById(UUID.fromString(postId));
    }

    @Override
    public void reportPost(PostDTO postDTO) {

    }

    @Override
    public List<PostDTO> getPostByType(int postType, @CurrentUser UserPrincipal currentUser) {

        return postRepository.findPostByPostTypeAndPostLocalizationOrderByCreatedAtDesc(postType,
                userRepository.findById(UUID.fromString(currentUser.getId())).orElseThrow().getPostalCode())
                .parallelStream()
                .map(this::buildPostDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CheerDTO addLike(CheerDTO cheerDTO, UserPrincipal currentUser) {

        Optional<Cheer> optionalCheer;

        if (cheerDTO.getCheerId() != null) {
            optionalCheer = cheerRepository.findById(UUID.fromString(cheerDTO.getCheerId()));
            if (optionalCheer.isPresent()) {
                optionalCheer.get().setActive(false);
                return buildCheerDTO(cheerRepository.save(optionalCheer.get()));
            }
        }

        Cheer cheer = new Cheer();

        cheer.setPost(postRepository.findById(UUID.fromString(cheerDTO.getPostId())).orElseThrow());
        cheer.setFellowshipUser(this.userRepository.findById(UUID.fromString(currentUser.getId())).orElseThrow());
        cheer.setActive(true);

        return buildCheerDTO(cheerRepository.save(cheer));
    }

    @Override
    public void removeLike(String likeId) {
        this.cheerRepository.deleteById(UUID.fromString(likeId));
    }

    private CheerDTO buildCheerDTO(Cheer cheer) {
        return CheerDTO.builder()
                .active(cheer.isActive())
                .cheerId(String.valueOf(cheer.getId()))
                .userId(String.valueOf(cheer.getFellowshipUser().getId()))
                .postId(String.valueOf(cheer.getPost().getId()))
                .build();
    }

    private PostDTO buildPostDTO(Post post) {

        PostDTO.PostDTOBuilder postDTOBuilder = PostDTO.builder();

        postDTOBuilder
                .postId(String.valueOf(post.getId()))
                .postType(post.getPostType())
                .postLocalization(post.getPostLocalization())
                .text(post.getText())
                .userId(String.valueOf(post.getFellowshipUser().getId()))
                .createdAt(post.getCreatedAt())
                .name(post.getFellowshipUser().getName())
                .profilePic(post.getFellowshipUser().getProfilePic())
                .tag(post.getTag())
                .propertyPrice(post.getPropertyPrice())
                .propertyType(post.getPropertyType());

        if (post.getMediaPosts() != null) {
            postDTOBuilder.mediaPosts(post.getMediaPosts().stream()
                    .map(dbMedia -> MediaPostDTO.builder().mediaType(dbMedia.getMediaType())
                            .mediaUrl(dbMedia.getMediaUrl())
                            .build())
                    .collect(Collectors.toList()));
        }

        if (post.getCheers() != null) {
            postDTOBuilder.likes(post.getCheers()
                    .stream()
                    .map(cheer -> CheerDTO.builder()
                            .active(cheer.isActive())
                            .cheerId(String.valueOf(cheer.getId()))
                            .userId(String.valueOf(cheer.getFellowshipUser().getId()))
                            .postId(String.valueOf(post.getId()))
                            .build())
                    .collect(Collectors.toList()));
        }

        if (post.getComments() != null) {
            postDTOBuilder.comments(post.getComments().stream()
                    .map(dbComments -> CommentDTO.builder()
                            .commentId(String.valueOf(dbComments.getId()))
                            .createdAt(dbComments.getCreated())
                            .text(dbComments.getText())
                            .postId(String.valueOf(post.getId()))
                            .userPic(dbComments.getFellowshipUser().getProfilePic())
                            .name(dbComments.getFellowshipUser().getName())
                            .userId(String.valueOf(dbComments.getFellowshipUser().getId()))
                            .build())
                    .collect(Collectors.toList()));
        }

        return postDTOBuilder.build();
    }

}
