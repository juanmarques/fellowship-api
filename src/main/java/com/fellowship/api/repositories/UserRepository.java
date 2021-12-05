package com.fellowship.api.repositories;

import com.fellowship.api.domain.entities.FellowshipUser;
import com.fellowship.api.domain.entities.Samples.NeighbourhoodsSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 20/11/2021
 */
@Repository
public interface UserRepository extends JpaRepository<FellowshipUser, UUID> {

    Optional<FellowshipUser> findByEmail(String email);

    @Query("select distinct user.postalCode as postalCode,user.neighbourhood as neighbourhood from FellowshipUser user")
    List<NeighbourhoodsSample> findAllNeighbourhoods();

}
