package in.gatekeeper.management.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Get the Authorization header from the request
        String authHeader = request.getHeader("Authorization");

        // Check if the header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extract the token from the header
            String token = authHeader.substring(7);

            // Here you would typically validate the token and set the authentication in the security context
            // For example:
            SecurityExpressionOperations jwtService;
            if (jwtService.validateToken(token)) {
                Authentication auth = jwtService.getAuthentication(token);
                 SecurityContextHolder.getContext().setAuthentication(auth);
             }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}

