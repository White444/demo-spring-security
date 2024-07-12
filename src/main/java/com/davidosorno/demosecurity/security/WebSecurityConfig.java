package com.davidosorno.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChainProtected(HttpSecurity http)
      throws Exception {

    http
        .cors((cors) -> cors.disable())
        .csrf((csrf) -> csrf.disable())
        .exceptionHandling(exception -> exception.accessDeniedPage("/"))
        .authorizeHttpRequests(
            (auth) -> auth
                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR) // Se utiliza en conjunto con denyAll() para bloquear las rutas que no se encuentran especificadas o no autorizadas.
                .permitAll()
                .requestMatchers(
                    HttpMethod.GET,
                    "/") // "/login"
                .permitAll()
                .requestMatchers(
                    HttpMethod.POST,
                    "/users/new")
                .permitAll()
                .requestMatchers(
                    HttpMethod.GET,
                    "/events")
                .hasRole("USER")
                .requestMatchers(
                    HttpMethod.GET,
                    "/events/*", "/events/**")
                .hasRole("ADMIN")
                .requestMatchers(
                    HttpMethod.POST,
                    "/events")
                .hasRole("USER")
                .requestMatchers(
                    HttpMethod.GET,
                    "/admin")
                .hasAnyRole("ADMIN", "SUPER_USER")
                .anyRequest()
                .denyAll())
        .formLogin((login -> login
            .usernameParameter("email")
            .loginPage("/") /* Tiene que ser exactamente la misma URL donde se encuentra el formulario de login y a donde se envía el formulario para ejecutar el login y tiene que ser agregada al permitAll(). */
            // .loginPage("/login")
            .defaultSuccessUrl("/events", true)))
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID"))
        .sessionManagement(session -> session
            .maximumSessions(1)
            .expiredUrl("/"));

    return http.build();
  }
}