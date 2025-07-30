package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.MemberDto;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.util.Utility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.RequestDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.format.DateTimeFormatter;
import java.security.Principal;
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

    // New Members List for Admin
    @GetMapping("/member/memberList")
    public String getNewMembers(Model model) {
        model.addAttribute("newMembers", memberService.getNewMemberByNewUserTrue());
        return "member/memberList";
    }


    //error
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode", statusCode);
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "페이지를 찾을 수 없습니다.");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMessage", "서버 내부 오류가 발생했습니다.");
            } else {
                model.addAttribute("errorMessage", "오류가 발생했습니다.");
            }
        }
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage.toString());
        }
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (exception != null) {
            model.addAttribute("exception", exception.toString());
        }
        return "error/error";
    }



    @GetMapping("/member/settings")
    public String settings(Model model, @AuthenticationPrincipal Object principal) {
        Member member = null;
        if (principal instanceof Member) {
            member = (Member) principal;
        } else if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = (String) oauthUser.getAttribute("email");
            if (email == null) {
                // Try to get email from other attributes if not directly available
                Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
                if (kakaoAccount != null) {
                    email = (String) kakaoAccount.get("email");
                }
                if (email == null) {
                    Map<String, Object> response = oauthUser.getAttribute("response"); // For Naver
                    if (response != null) {
                        email = (String) response.get("email");
                    }
                }
            }
            if (email != null) {
                member = memberService.getMemberByEmail(email);
            }
        }

        if (member != null) {
            model.addAttribute("member", member);
        } else {
            // Handle case where member is not found or not authenticated properly
            // Redirect to login or error page
            return "redirect:/login"; // Or handle error appropriately
        }
        return "member/settings";
    }

    @PostMapping("/member/settings")
    public String updateMemberSettings(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
        try {
            // Get the authenticated member to ensure only allowed fields are updated
            Member authenticatedMember = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (authenticatedMember == null) {
                redirectAttributes.addFlashAttribute("error", "인증된 사용자 정보를 찾을 수 없습니다.");
                return "redirect:/member/settings";
            }

            // Update only allowed fields from memberDto to authenticatedMember
            authenticatedMember.setName(memberDto.getName());
            authenticatedMember.setPhoneNumber(memberDto.getPhoneNumber());

            memberService.updateMember(authenticatedMember);
            redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/member/settings";
    }

    @PostMapping("/member/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmNewPassword,
                                 RedirectAttributes redirectAttributes) {
        try {
            Member authenticatedMember = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (authenticatedMember == null) {
                redirectAttributes.addFlashAttribute("error", "인증된 사용자 정보를 찾을 수 없습니다.");
                return "redirect:/member/settings";
            }

            if (!memberService.authenticate(currentPassword, authenticatedMember.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
                return "redirect:/member/settings";
            }

            if (!newPassword.equals(confirmNewPassword)) {
                redirectAttributes.addFlashAttribute("error", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
                return "redirect:/member/settings";
            }

            memberService.changePassword(authenticatedMember, newPassword);
            redirectAttributes.addFlashAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "비밀번호 변경 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/member/settings";
    }

    @GetMapping("/member/profile")
    public String profile(Model model, @AuthenticationPrincipal Object principal) {
        Member member = null;
        if (principal instanceof Member) {
            member = (Member) principal;
        } else if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = (String) oauthUser.getAttribute("email");
            if (email == null) {
                Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
                if (kakaoAccount != null) {
                    email = (String) kakaoAccount.get("email");
                }
                if (email == null) {
                    Map<String, Object> response = oauthUser.getAttribute("response");
                    if (response != null) {
                        email = (String) response.get("email");
                    }
                }
            }
            if (email != null) {
                member = memberService.getMemberByEmail(email);
            }
        }

        if (member != null) {
            model.addAttribute("member", member);
            if (member.getCreateTime() != null) {
                model.addAttribute("createTimeFormatted", Utility.formatLocalDateTime(member.getCreateTime()));
            }
        } else {
            return "redirect:/login";
        }
        return "member/profile";
    }

    @PostMapping("/member/delete")
    public String deleteMember(RedirectAttributes redirectAttributes) {
        try {
            Member authenticatedMember = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (authenticatedMember == null) {
                redirectAttributes.addFlashAttribute("error", "인증된 사용자 정보를 찾을 수 없습니다.");
                return "redirect:/member/settings";
            }

            memberService.deleteMember(authenticatedMember);
            SecurityContextHolder.clearContext(); // Invalidate session
            redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원 탈퇴 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/";
    }

}

