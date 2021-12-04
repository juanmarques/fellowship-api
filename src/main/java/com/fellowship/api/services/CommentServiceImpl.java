package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CommentDTO;
import com.fellowship.api.domain.entities.Comment;
import com.fellowship.api.repositories.CommentRepository;
import com.fellowship.api.repositories.PostRepository;
import com.fellowship.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Created by Juan Marques on 04/12/2021
 */
@Service
@Log4j2
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setFellowshipUser(userRepository.findById(UUID.fromString(commentDTO.getUserId())).orElseThrow());
        comment.setPost(postRepository.findById(commentDTO.getPostId()).orElseThrow());
        comment.setText(commentDTO.getText());
        comment.setCreated(commentDTO.getCreatedAt());

        return buildDTO(this.commentRepository.save(comment));
    }

    @Override
    public void deleteComment(CommentDTO commentDTO) {
        this.commentRepository.deleteById(commentDTO.getCommentId());
    }

    @Override
    public void reportComment(CommentDTO commentDTO) {

    }

    private CommentDTO buildDTO(Comment entity) {

        return CommentDTO.builder()
                .commentId(String.valueOf(entity.getId()))
                .name(entity.getFellowshipUser().getName())
                .userPic(entity.getFellowshipUser().getProfilePic())
                .createdAt(entity.getCreated())
                .postId(String.valueOf(entity.getPost().getId()))
                .text(entity.getText())
                .build();
    }
}
