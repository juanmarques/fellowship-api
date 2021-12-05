package com.fellowship.api.repositories;

import com.fellowship.api.domain.entities.Cheer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 05/12/2021
 */
public interface CheerRepository extends JpaRepository<Cheer, UUID> {


    @Query("SELECT u FROM Cheer u WHERE u.fellowshipUser.id=?1 AND u.post.id= ?2")
    Optional<Cheer> findByUserIdAndPostId(UUID userId,UUID postId);
}
