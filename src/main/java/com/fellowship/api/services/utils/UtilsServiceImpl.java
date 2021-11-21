package com.fellowship.api.services.utils;

import com.fellowship.api.domain.dtos.NeighbourhoodsDTO;
import com.fellowship.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@Service
@Log4j2
@AllArgsConstructor
public class UtilsServiceImpl implements UtilsService {

    private final UserRepository userRepository;

    @Override
    public List<NeighbourhoodsDTO> findAllNeighbourhoods() {

        return userRepository.findAllNeighbourhoods().parallelStream()
                .map(neighbourhoodsSample -> NeighbourhoodsDTO.builder()
                        .label(neighbourhoodsSample.getNeighbourhood())
                        .id(neighbourhoodsSample.getPostalCode())
                        .build())
                .collect(Collectors.toList());

    }
}
