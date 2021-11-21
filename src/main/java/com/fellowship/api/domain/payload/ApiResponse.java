package com.fellowship.api.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Juan Marques
 *
 */
@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;

}
