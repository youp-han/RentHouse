package com.jjst.rentManagement.renthouse.config;

import com.jjst.rentManagement.renthouse.Service.CustomUserDetailsService;
import com.jjst.rentManagement.renthouse.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private static final String OAUTH_NAVER = "naver";
    private static final String OAUTH_KAKAO = "kakao";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/login**", "/error**", "/member/**", "/sample").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                        //.requestMatchers("/admin/**").hasRole("ADMIN")  // Add this line to restrict access to /admin/** for admin role
                        .requestMatchers(
                                "/img/**",
                                "/css/**",
                                "/js/**",
                                "/vendor/**",
                                "/scss/**",
                                "/public/**",
                                "/interface/**",
                                "/selfservice/**",
                                "/helpservice/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email") // Map form's "email" field
                        .passwordParameter("password") // Map form's "password" field
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint()
                        .userService(oAuth2UserService())
                        .and()
                        .failureHandler(authenticationFailureHandler())
                )
                .userDetailsService(customUserDetailsService); // Custom implementation for DB-based users

        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return (userRequest) -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String userNameAttributeName = userRequest.getClientRegistration()
                    .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

            Map<String, Object> mappedAttributes = mapAttributes(registrationId, new HashMap<>(oAuth2User.getAttributes()));

            OAuth2UserAuthority authority = new OAuth2UserAuthority(mappedAttributes);

            return new DefaultOAuth2User(Collections.singleton(authority), mappedAttributes, userNameAttributeName);
        };
    }

    private Map<String, Object> mapAttributes(String registrationId, Map<String, Object> attributes) {
        if (OAUTH_NAVER.equals(registrationId)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            if (response != null) {
                attributes.put("name", response.get("name"));
            }
        } else if (OAUTH_KAKAO.equals(registrationId)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            if (kakaoAccount != null) {
                attributes.put("nickname", kakaoAccount.get("profile"));
            }
        }
        return attributes;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(); // Custom logic on login failure
    }
}
