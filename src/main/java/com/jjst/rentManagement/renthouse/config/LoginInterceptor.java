package com.jjst.rentManagement.renthouse.config;

import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.util.Utility;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/**
 * Interceptor to handle user authentication and set user attributes in the request.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final MemberService memberService;

    /**
     * Constructor for LoginInterceptor.
     * @param memberService Service to handle member-related operations.
     */
    public LoginInterceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Pre-handle method to process user authentication before the request reaches the controller.
     * @param request HTTP request.
     * @param response HTTP response.
     * @param handler Handler object.
     * @return true if the request should proceed, false otherwise.
     * @throws Exception if an error occurs during processing.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            // If no user is authenticated, redirect to the home page.
            // response.sendRedirect("/");
            // return false;
        }

        // Handle OAuth2 authentication.
        if (principal instanceof OAuth2AuthenticationToken) {
            return handleOAuth2Authentication(request, response, (OAuth2AuthenticationToken) principal);
        }
        // Handle standard authentication.
        else if (principal instanceof Authentication) {
            return handleStandardAuthentication(request, (Authentication) principal);
        }

        // Allow the request to proceed if no specific authentication is required.
        return true;
    }

    /**
     * Handles OAuth2 authentication by extracting user attributes and setting them in the request.
     * @param request HTTP request.
     * @param response HTTP response.
     * @param authToken OAuth2 authentication token.
     * @return true if authentication is successful, false otherwise.
     * @throws Exception if an error occurs during processing.
     */
    private boolean handleOAuth2Authentication(HttpServletRequest request, HttpServletResponse response,
                                               OAuth2AuthenticationToken authToken) throws Exception {
        // Extract attributes from the OAuth2 token.
        Map<String, Object> attributes = authToken.getPrincipal().getAttributes();
        Object responseObj = attributes.get("response");

        // Check if the response contains user information.
        if (responseObj instanceof Map) {
            Map<String, Object> responseAttributes = (Map<String, Object>) responseObj;
            String id = (String) responseAttributes.get("email");

            // Retrieve the member using the extracted email.
            Member member = memberService.getMemberBySnsId(Utility.extractUsername(id));
            if (member != null) {
                // Set user attributes in the request and allow the request to proceed.
                setUserAttributes(request, member);
                return true;
            } else {
                // Redirect to the member join page if the user is not found.
                response.sendRedirect("/member/join");
                return false;
            }
        }

        // Redirect to the home page if the response does not contain valid user information.
        response.sendRedirect("/");
        return false;
    }

    /**
     * Handles standard authentication by setting user attributes in the request.
     * @param request HTTP request.
     * @param authentication Authentication object.
     * @return true if authentication is successful.
     */
    private boolean handleStandardAuthentication(HttpServletRequest request, Authentication authentication) {
        // Retrieve the authenticated member and set user attributes.
        Member member = (Member) authentication.getPrincipal();
        setUserAttributes(request, member);
        return true;
    }

    /**
     * Sets user attributes in the request and updates the SecurityContext.
     * @param request HTTP request.
     * @param member Authenticated member.
     */
    private void setUserAttributes(HttpServletRequest request, Member member) {
        // Set user details as request attributes.
        request.setAttribute("name", member.getName());
        request.setAttribute("email", member.getEmail());
        request.setAttribute("role", member.getRole());

        // Update the SecurityContext with the authenticated user's details.
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        member,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority(member.getRole()))
                )
        );
    }

    /**
     * Post-handle method to add user attributes to the ModelAndView after the request is processed.
     * @param request HTTP request.
     * @param response HTTP response.
     * @param handler Handler object.
     * @param modelAndView ModelAndView object.
     * @throws Exception if an error occurs during processing.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            // Add user attributes to the ModelAndView for use in the view layer.
            modelAndView.addObject("name", request.getAttribute("name"));
            modelAndView.addObject("email", request.getAttribute("email"));
            modelAndView.addObject("role", request.getAttribute("role"));
        }
    }
}
