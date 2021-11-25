package com.fellowship.api.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by Juan Marques on 24/11/2021
 */
@Data
@Builder
public class MediaPostDTO implements Serializable {
    private String mediaUrl;
    private String mediaType;
}
