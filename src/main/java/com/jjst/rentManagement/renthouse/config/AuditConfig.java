package com.jjst.rentManagement.renthouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;   // ← 여기!


// 1) Auditing 활성화
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    // 2) 현재 사용자명을 반환하는 AuditorAware 빈
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                // 아직 로그인 전이거나 인증 정보가 없으면 기본값 리턴
                if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
                    return Optional.of("SYSTEM");
                }
                return Optional.of(auth.getName());
            }
        };
    }
}
