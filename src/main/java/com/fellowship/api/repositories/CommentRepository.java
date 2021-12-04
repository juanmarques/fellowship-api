package com.fellowship.api.repositories;

import com.fellowship.api.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
public interface CommentRepository extends JpaRepository<Comment, String> {

    void deleteById(UUID commentId);
}
