package com.ohgiraffers.midnight.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public StopWatchInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler) throws Exception {
        HttpSession session = request.getSession();

        if ((String) session.getAttribute("token") == null) {
            session.setAttribute("failMessage", "로그인을 먼저 해주세요.");
            response.sendRedirect("/user/login");
            return false;
        }

        try {
            jwtUtil.validateToken((String) session.getAttribute("token"), (String) session.getAttribute("username"));
            return true;
        } catch (Exception e) {
            session.setAttribute("failMessage", "로그인을 먼저 해주세요.");
            response.sendRedirect("/user/login");
            return false;
        }
    }
}

