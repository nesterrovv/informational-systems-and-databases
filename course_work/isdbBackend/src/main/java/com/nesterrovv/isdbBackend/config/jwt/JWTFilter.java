package com.nesterrovv.isdbBackend.config.jwt;

import com.nesterrovv.isdbBackend.config.jwt.JWTProvider;
import com.nesterrovv.isdbBackend.services.CourierService;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
@Log
public class JWTFilter extends GenericFilterBean {
    public static final String AUTHORIZATION = "Authorization";
    private final JWTProvider jwtProvider;
    private final com.nesterrovv.isdbBackend.services.CourierService courierService;

    public JWTFilter(JWTProvider jwtProvider, CourierService courierService) {
        this.jwtProvider = jwtProvider;
        this.courierService = courierService;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("do filter...");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && jwtProvider.validateToken(token)) {
            String username= jwtProvider.getLoginFromToken(token);
            UserDetails customUserDetails = courierService.loadCourierByUsername(username);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}