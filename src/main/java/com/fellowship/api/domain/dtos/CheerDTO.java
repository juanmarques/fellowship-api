package com.fellowship.api.domain.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * @author Created by Juan Marques on 05/12/2021
 */
@Data
@Builder
public class CheerDTO {

    private String cheerId;
    private String postId;
    private String userId;
    private boolean active;
}
