package com.jjst.rentManagement.renthouse.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

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

    @GetMapping("/member/settings")
    public String settings(Model model, @AuthenticationPrincipal OAuth2User oauthUser) {
        // 현재 로그인한 사용자 정보 가져오기
        // CustomUserDetailsService에서 Member 객체를 반환하도록 설정되어 있다면 직접 Member 객체를 가져올 수 있습니다.
        // 여기서는 OAuth2User를 통해 정보를 가져오는 예시를 보여줍니다.
        if (oauthUser != null) {
            String email = (String) oauthUser.getAttribute("email");
            // 실제 MemberService를 통해 Member 객체를 가져와야 합니다.
            // Member member = memberService.getMemberByEmail(email);
            // model.addAttribute("member", member);
            model.addAttribute("userEmail", email);
            model.addAttribute("userName", oauthUser.getAttribute("name"));
            // TODO: 실제 Member 객체에서 전화번호, 주소 등 추가 정보 로드
        }
        return "member/settings";
    }

    @PostMapping("/member/settings")
    public String updateMemberSettings(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
        try {
            // TODO: MemberService를 통해 사용자 정보 업데이트 로직 구현
            // memberService.updateMember(memberDto);
            redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/member/settings";
    }


}
