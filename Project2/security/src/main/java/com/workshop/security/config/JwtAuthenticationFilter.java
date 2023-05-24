package com.workshop.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        System.out.println("Before first if condition in doFilterInternal");
//        if (authHeader == null || authHeader.startsWith("Bearer ")) {
        if (authHeader == null) {
            System.out.println("First if was trueeeeeee, authHeader: " + authHeader);
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);

        userEmail = jwtService.extractUsername(jwt);
        System.out.println("INSIDE DOFILTERINTERNAL, userEmail: " + userEmail);
        // If we have a userEmail and user is not authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Get user details from the database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // If the user and the token is valid
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // store request details in the authentication token
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Update the authentication token
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Pass on the request/response to the next filter/entity in the chain
        filterChain.doFilter(request, response);
    }
}
