package com.fellowship.api.controllers;

import com.fellowship.api.domain.dtos.CommentDTO;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import com.fellowship.api.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by Juan Marques on 04/12/2021
 */
@RequestMapping("comment")
@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    private ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    @DeleteMapping("/delete/{commentId}")
    private void deleteComment(@PathVariable String commentId) {
        this.commentService.deleteComment(commentId);
    }

    @PostMapping("/report")
    private void reportComment(@RequestBody CommentDTO commentDTO, @CurrentUser UserPrincipal currentUser) {

    }
}
