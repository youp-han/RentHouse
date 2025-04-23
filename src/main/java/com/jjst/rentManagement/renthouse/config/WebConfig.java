package com.jjst.rentManagement.renthouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig class to configure Spring MVC settings.
 * This class is used to register interceptors and other web-related configurations.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // LoginInterceptor instance to handle authentication and user-related logic.
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * Registers interceptors for handling HTTP requests.
     * The LoginInterceptor is applied to all paths except for the excluded ones.
     *
     * @param registry InterceptorRegistry to register interceptors.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // Register the LoginInterceptor.
                .addPathPatterns("/**") // Apply the interceptor to all paths.
                .excludePathPatterns("/login", "/logout"); // Exclude login and logout paths from interception.
    }
}
