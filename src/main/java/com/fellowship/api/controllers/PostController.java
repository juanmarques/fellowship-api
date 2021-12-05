package com.fellowship.api.controllers;

import com.fellowship.api.domain.dtos.CheerDTO;
import com.fellowship.api.domain.dtos.PostDTO;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import com.fellowship.api.services.CheerService;
import com.fellowship.api.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@RequestMapping("post")
@RestController
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final CheerService cheerService;

    @PostMapping("/create")
    private ResponseEntity<?> createPost(@RequestBody PostDTO postDTO, @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(postService.createPost(postDTO, currentUser));
    }

    @GetMapping("/{postType}")
    private ResponseEntity<?> getPostByType(@PathVariable int postType, @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(postService.getPostByType(postType, currentUser));
    }

    @DeleteMapping("/delete/{postId}")
    private void deletePost(@PathVariable String postId) {
        this.postService.deletePost(postId);
    }

    @PostMapping("/report")
    private void reportPost(@RequestBody PostDTO postDTO, @CurrentUser UserPrincipal currentUser) {

    }

    @PostMapping("/add-like")
    private ResponseEntity<?> addLike(@RequestBody CheerDTO cheerDTO, @CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(cheerService.addLike(cheerDTO, currentUser));
    }

    @PostMapping("/remove-like/{likeId}")
    private void removeLike(@PathVariable String likeId) {
        this.cheerService.removeLike(likeId);
    }
}
