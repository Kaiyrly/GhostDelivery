package com.milestone2.app.filters;

import com.milestone2.app.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JwtRequestFilterTest {

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain filterChain;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtRequestFilter, "jwtUtil", jwtUtil);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void doFilterInternalTest() throws Exception {
        when(request.getHeader(anyString())).thenReturn("Bearer sometoken");
        when(jwtUtil.extractUsername(anyString())).thenReturn("testuser");
        when(jwtUtil.validateToken(anyString(), anyString())).thenReturn(true);
        
        jwtRequestFilter.doFilterInternal(request, response, filterChain);
        
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilterInternalTestNoBearerToken() throws Exception {
        when(request.getHeader(anyString())).thenReturn("sometoken");
        
        jwtRequestFilter.doFilterInternal(request, response, filterChain);
        
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
