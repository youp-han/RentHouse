package com.jjst.rentManagement.Rent_House.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User user, Model model) {
        if (user != null) {
            String name = null;
            // Kakao 사용자 정보 처리
            Map<String, Object> nicknameMap = (Map<String, Object>) user.getAttribute("nickname");
            if (nicknameMap != null) {
                name = (String) nicknameMap.get("nickname");
            } else if (user.getAttribute("name") != null) {
                // Naver 사용자 정보 처리
                name = user.getAttribute("name");
            }
            model.addAttribute("name", name);
        }
        return "index";
    }

    boolean isApplicationUser(OAuth2User user){

        //check logged in user

        return false;
    }

    @GetMapping("/login")
    public String login() {
        return "login";

    }
}
