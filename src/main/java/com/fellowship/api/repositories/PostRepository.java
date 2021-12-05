package com.fellowship.api.repositories;

import com.fellowship.api.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findPostByPostTypeAndPostLocalizationOrderByCreatedAtDesc(int postType,String postLocalization);
}
