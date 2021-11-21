package com.fellowship.api.security.configuration;

/**
 * @author Juan Marques
 *
 */
public class SecurityConstants {
    
    public static final String SECRET = "P0upT0pS&Cret";
    public static final long EXPIRATION_TIME = 86_400_000; // 2 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/person/sign-up";
}
