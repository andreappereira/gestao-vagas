package com.andreappereira.main.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.andreappereira.main.providers.JWTCompanyProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {
    @Autowired
    private JWTCompanyProvider jwtCompanyProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        // SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("/company")) {
            if(header != null) {
                var token = this.jwtCompanyProvider.validateToken(header);
    
                if(token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                var subjectToken = token.getSubject();
    
                request.setAttribute("company_id", subjectToken);
                var roles = token.getClaim("roles").asList(Object.class);

                var grants = roles.stream().map(
                    role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase())
                ).toList();
    
                UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(subjectToken, null, grants);
                
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }


}
