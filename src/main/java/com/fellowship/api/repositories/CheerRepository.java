package com.fellowship.api.repositories;

import com.fellowship.api.domain.entities.Cheer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Created by Juan Marques on 05/12/2021
 */
public interface CheerRepository extends JpaRepository<Cheer, UUID> {
}
