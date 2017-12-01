package com.okrawczy.restaurantsfinder.security;

import com.okrawczy.restaurantsfinder.service.ClientDetailsServiceImpl;
import com.okrawczy.restaurantsfinder.service.OwnerDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Created by Olaf on 2017-11-10.
 */

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Configuration
    @Order(2)
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private ClientDetailsServiceImpl clientDetailsService;
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/clients/new", "/login",
                            "/restaurantsCities", "/availableCuisines",
                            "/restaurants/findByParameters",
                            "/restaurants/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    // this disables session creation on Spring Security
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(clientDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/h2-console/**")
                    .and()
                    .ignoring().antMatchers("/swagger-ui.html/**")
                    .and()
                    .ignoring().antMatchers("/swagger-resources/**")
                    .and()
                    .ignoring().antMatchers("/v2/api-docs/**")
                    .and()
                    .ignoring().antMatchers("/**/favicon.ico")
                    .and()
                    .ignoring().antMatchers("/webjars/**");
        }
    }

    @Configuration
    @Order(1)
    public static class WebSecurityConfigOwner extends WebSecurityConfigurerAdapter {

        @Autowired
        private OwnerDetailsServiceImpl ownerDetailsService;
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/owners/**").cors().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/owners/**", "/owners/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterAfter(new JWTAuthenticationFilterOwner(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(ownerDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }
    }
}