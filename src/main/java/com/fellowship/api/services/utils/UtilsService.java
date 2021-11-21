package com.fellowship.api.services.utils;

import com.fellowship.api.domain.dtos.NeighbourhoodsDTO;

import java.util.List;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
public interface UtilsService {
    List<NeighbourhoodsDTO> findAllNeighbourhoods();
}
