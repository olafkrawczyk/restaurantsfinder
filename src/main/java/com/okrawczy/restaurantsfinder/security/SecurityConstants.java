package com.okrawczy.restaurantsfinder.security;

/**
 * Created by Olaf on 2017-11-10.
 */


public class SecurityConstants {
    public static final String SECRET = "!TableRsv?69";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/clients/new";
}