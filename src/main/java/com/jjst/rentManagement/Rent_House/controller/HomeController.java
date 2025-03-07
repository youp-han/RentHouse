package com.jjst.rentManagement.Rent_House.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User user, Model model) {
        if (user != null) {
            model.addAttribute("name", user.getAttribute("name"));
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
