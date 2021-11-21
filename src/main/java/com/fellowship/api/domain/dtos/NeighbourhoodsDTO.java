package com.fellowship.api.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@Data
@Builder
public class NeighbourhoodsDTO implements Serializable {

    private String id;
    private String label;
}
