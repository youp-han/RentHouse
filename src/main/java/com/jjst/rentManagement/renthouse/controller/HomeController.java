package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.Service.MemberService;
import com.jjst.rentManagement.renthouse.dto.LoginDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;


    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            Member member = (Member) ((Authentication) principal).getPrincipal();
            model.addAttribute("name", member.getName());
            model.addAttribute("email", member.getEmail());
            model.addAttribute("role", member.getRole());
        }
        return "index";
    }


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

    boolean isApplicationUser(OAuth2User user){

        //check logged in user

        return false;
    }

    @PostMapping("/login/authenticate")
    @ResponseBody
    public ResponseEntity<String> loginAuthenticate(@RequestBody LoginDto lgDto, HttpServletRequest request) {
        Member member = memberService.getMemberByEmail(lgDto.getEmail());
        if (member != null) {
            if (memberService.authenticate(lgDto.getPassword(), member.getPassword())) {
                // Create a custom Principal or use Spring Security Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                member,
                                null,
                                Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Create a new session and add the security context.
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

                return ResponseEntity.ok("success");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/";
    }

    @GetMapping("/error")
    public String handleError() {
        return "/error/"; // Ensure 'error.html' or equivalent exists in templates
    }
}
