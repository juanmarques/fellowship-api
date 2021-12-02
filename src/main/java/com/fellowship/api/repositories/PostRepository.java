package com.fellowship.api.repositories;

import com.fellowship.api.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findPostByPostTypeOrderByCreatedAtDesc(int postType);

}
