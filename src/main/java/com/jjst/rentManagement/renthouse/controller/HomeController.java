package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

    private int count = 0;
    //home
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        String returnString ="index";
        if (principal != null) {
            if (principal instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) principal;
                Map<String, Object> attributes = authToken.getPrincipal().getAttributes();
                Map<String, Object> response = (Map<String, Object>) attributes.get("response");
                String id = (String) response.get("email");
                Member member = memberService.getMemberBySnsId(Utility.extractUsername(id)); // memberService는 Member를 검색하는 서비스 클래스입니다.

                if (member != null) {
                    model.addAttribute("name", member.getName());
                    model.addAttribute("email", member.getEmail());
                    model.addAttribute("role", member.getRole());
                } else {
                    returnString = "/member/join"; // 회원가입 페이지로 이동
                }
            } else if (principal instanceof Authentication) {
                Member member = (Member) ((Authentication) principal).getPrincipal();
                model.addAttribute("name", member.getName());
                model.addAttribute("email", member.getEmail());
                model.addAttribute("role", member.getRole());
            }
        }
        count ++;
        System.out.println(String.format("count ============================================ %d", count));
        return returnString;
    }

    boolean isApplicationUser(OAuth2User user){

        //check logged in user

        return false;
    }

    //login
    @GetMapping("/login")
    public String login(){
        count=0;
        return "login";
    }

    //logout
    @GetMapping("/logout")
    public String logout(){
        count=0;
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
