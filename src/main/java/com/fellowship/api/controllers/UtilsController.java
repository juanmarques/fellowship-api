package com.fellowship.api.controllers;

import com.fellowship.api.services.utils.UtilsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@RestController
@AllArgsConstructor
@RequestMapping("utils")
public class UtilsController {

    private final UtilsService utilsService;

    @GetMapping("/neighbourhoods")
    private ResponseEntity<?> loadUserProfile() {
        return ResponseEntity.ok(utilsService.findAllNeighbourhoods());
    }
}
