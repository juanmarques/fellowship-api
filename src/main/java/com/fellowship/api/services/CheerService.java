package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.CheerDTO;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;

/**
 * @author Created by Juan Marques on 05/12/2021
 */
public interface CheerService {
    CheerDTO addLike(CheerDTO cheerDTO, @CurrentUser UserPrincipal currentUser);

    void removeLike(String likeId);
}
