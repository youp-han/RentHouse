package com.jjst.rentManagement.renthouse.config;

import com.jjst.rentManagement.renthouse.service.CustomUserDetailsService;
import com.jjst.rentManagement.renthouse.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSecurityConfig class to configure Spring Security settings.
 * This class defines security rules, OAuth2 login handling, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Custom service to load user details from the database.
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Constants for OAuth2 providers.
    private static final String OAUTH_NAVER = "naver";
    private static final String OAUTH_KAKAO = "kakao";

    /**
     * Configures the security filter chain for HTTP requests.
     * Defines which paths are accessible without authentication and sets up OAuth2 login.
     *
     * @param http HttpSecurity object to configure security settings.
     * @return Configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection for simplicity (not recommended for production).
                .authorizeHttpRequests((requests) -> requests
                        // Define public paths that do not require authentication.
                        .requestMatchers("/", "/login/**", "/error**", "/member/**", "/sample").permitAll()
                        //.requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict admin paths to ADMIN role.

                        // Allow access to static resources.
                        .requestMatchers(
                                "/img/**",
                                "/css/**",
                                "/js/**",
                                "/vendor/**",
                                "/scss/**"
                        ).permitAll()

                        // All other requests require authentication.
                        .anyRequest().authenticated()
                )
                // Configure OAuth2 login settings.
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login") // Custom login page.
                        .userInfoEndpoint()
                        .userService(oAuth2UserService()) // Custom OAuth2 user service.
                        .and()
                        .failureHandler(authenticationFailureHandler()) // Custom failure handler for login errors.
                )
                // Use custom user details service for database-based authentication.
                .userDetailsService(customUserDetailsService);

        return http.build();
    }

    /**
     * Custom OAuth2UserService to map user attributes from OAuth2 providers.
     *
     * @return Configured OAuth2UserService.
     */
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return (userRequest) -> {
            // Load user information from the OAuth2 provider.
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            // Get the registration ID (e.g., "naver", "kakao").
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            // Get the attribute name used to identify the user.
            String userNameAttributeName = userRequest.getClientRegistration()
                    .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

            // Map the attributes based on the provider.
            Map<String, Object> mappedAttributes = mapAttributes(registrationId,
                    new HashMap<>(oAuth2User.getAttributes()));

            // Create a new OAuth2UserAuthority with the mapped attributes.
            OAuth2UserAuthority authority = new OAuth2UserAuthority(mappedAttributes);

            // Return a DefaultOAuth2User with the mapped attributes and authority.
            return new DefaultOAuth2User(Collections.singleton(authority), mappedAttributes, userNameAttributeName);
        };
    }

    /**
     * Maps user attributes from OAuth2 providers (e.g., Naver, Kakao).
     *
     * @param registrationId The ID of the OAuth2 provider.
     * @param attributes The original attributes from the provider.
     * @return Mapped attributes.
     */
    private Map<String, Object> mapAttributes(String registrationId, Map<String, Object> attributes) {
        if (OAUTH_NAVER.equals(registrationId)) {
            // Map attributes for Naver.
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            if (response != null) {
                attributes.put("name", response.get("name"));
                attributes.put("email", response.get("email"));
                attributes.put("nickname", response.get("nickname"));
            }
        } else if (OAUTH_KAKAO.equals(registrationId)) {
            // Map attributes for Kakao.
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            if (kakaoAccount != null) {
                attributes.put("nickname", kakaoAccount.get("profile"));
            }
        }
        return attributes;
    }

    /**
     * Custom authentication failure handler to handle login errors.
     *
     * @return Configured AuthenticationFailureHandler.
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(); // Custom logic on login failure.
    }

    /**
     * Configures paths to ignore for security (e.g., static resources).
     *
     * @return Configured WebSecurityCustomizer.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/vendor/**",
                "/css/**",
                "/img/**",
                "/js/**",
                "/favicon.ico",
                "/static/**"
        );
    }

    /**
     * Configures the password encoder for encoding and verifying passwords.
     *
     * @return Configured PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding.
    }
}
