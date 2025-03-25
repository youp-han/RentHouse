package com.jjst.rentManagement.renthouse.handler;

import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@Component

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            if (principal instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) principal;
                Map<String, Object> attributes = authToken.getPrincipal().getAttributes();
                Map<String, Object> responseAttributes = (Map<String, Object>) attributes.get("response");
                String id = (String) responseAttributes.get("email");
                Member member = memberService.getMemberBySnsId(Utility.extractUsername(id));

                if (member != null) {
                    request.setAttribute("name", member.getName());
                    request.setAttribute("email", member.getEmail());
                    request.setAttribute("role", member.getRole());
                } else {
                    response.sendRedirect("/member/join");
                    return false;
                }
            } else if (principal instanceof Authentication) {
                Member member = (Member) ((Authentication) principal).getPrincipal();
                request.setAttribute("name", member.getName());
                request.setAttribute("email", member.getEmail());
                request.setAttribute("role", member.getRole());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("name", request.getAttribute("name"));
            modelAndView.addObject("email", request.getAttribute("email"));
            modelAndView.addObject("role", request.getAttribute("role"));
        }
    }
}
