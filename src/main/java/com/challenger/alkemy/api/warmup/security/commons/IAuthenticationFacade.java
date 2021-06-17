package com.challenger.alkemy.api.warmup.security.commons;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
