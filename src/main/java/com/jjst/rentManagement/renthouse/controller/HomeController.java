package com.jjst.rentManagement.renthouse.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    //home
    @GetMapping("/")
    public String home() {
        return "index";
    }

    //login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //logout
    @GetMapping("/logout")
    public String logout(){
        return "/";
    }

    //join
    @GetMapping("/member/join")
    public String applyToJoin(){
        return "/member/join";
    }


    //error
    @GetMapping("/error")
    public String handleError() {
        return "/error/"; // Ensure 'error.html' or equivalent exists in templates
    }

    //sample

    @GetMapping("/sample")
    public String sampleHome(@AuthenticationPrincipal OAuth2User user, Model model) {
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
        return "sample";
    }


}
