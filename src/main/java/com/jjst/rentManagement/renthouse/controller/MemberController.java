package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.MemberDto;
import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.dto.LoginDto;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.common.enums.RoleType;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.jjst.rentManagement.renthouse.util.Utility;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.stream.Collectors;

@RequestMapping("/member")
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private EntityConverter entityConverter;

    @GetMapping("/newMembers")
    public String applyList(Model model){

        List<Member> memberList = memberService.getNewMemberByNewUserTrue();
        List<Property> propertyList= propertyService.getAllProperties();

        List<PropertyDto> propertyDtoList = new ArrayList<>();

        for (Property item : propertyList){
            PropertyDto propertyDto = new PropertyDto();
            propertyDto.setPropertyId(item.getId());
            propertyDto.setAddress(item.getAddress());
            propertyDto.setName(item.getName());
            propertyDtoList.add(propertyDto);
        }

        model.addAttribute("newMembers", memberList);
        model.addAttribute("propertyList", propertyDtoList);
        return "/admin/applyList";
    }

    @GetMapping("/members")
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAll();
        List<MemberDto> memberDtos = members.stream()
                .map(member -> entityConverter.convertToDto(member, MemberDto.class))
                .collect(Collectors.toList());
        model.addAttribute("members", memberDtos);
        return "admin/memberList";
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<String> loginAuthenticate( @RequestBody LoginDto lgDto, HttpServletRequest request) {
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

    @PostMapping("/save")
    public String saveMemberApplication(@ModelAttribute MemberDto memberDto, RedirectAttributes redirectAttributes) {
        try {
            memberService.processMemberApplication(memberDto);
            redirectAttributes.addFlashAttribute("message", "회원 가입이 성공적으로 처리되었습니다.");
            return "redirect:/member/join";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "회원 가입 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/member/join";
        }
    }

    @GetMapping("/check-email")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public boolean checkEmail(@RequestParam String email){
        email = email.toLowerCase().trim();
        boolean result = memberService.checkEmail(email);
        return result;
    }

    // Admin API to get member by ID
    @GetMapping("/{id}")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
        try {
            Member member = memberService.getById(id);
            if (member != null) {
                MemberDto memberDto = entityConverter.convertToDto(member, MemberDto.class);
                return ResponseEntity.ok(memberDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Admin API to update member details (role, approved, deleted)
    @PutMapping("/{id}")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public ResponseEntity<Map<String, String>> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        Map<String, String> response = new java.util.HashMap<>();
        try {
            Member existingMember = memberService.getById(id);
            existingMember.setRole(RoleType.valueOf(memberDto.getRole()));
            existingMember.setApproved(memberDto.isApproved());
            existingMember.setDeleted(memberDto.isDeleted());
            
            existingMember.setPhoneNumber(memberDto.getPhoneNumber()); // Allow phone number update

            memberService.save(existingMember);
            response.put("status", "success");
            response.put("message", "회원 정보가 성공적으로 업데이트되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "회원 정보 업데이트 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Admin API to approve a new member
    @PostMapping("/approve/{id}")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public ResponseEntity<Map<String, String>> approveMember(@PathVariable Long id) {
        Map<String, String> response = new java.util.HashMap<>();
        try {
            Member member = memberService.getById(id);
            member.setApproved(true);
            member.setNewUser(false);
            memberService.save(member);
            response.put("status", "success");
            response.put("message", "회원이 성공적으로 승인되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "회원 승인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Admin API to reject and delete a new member
    @PostMapping("/reject/{id}")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public ResponseEntity<Map<String, String>> rejectMember(@PathVariable Long id) {
        Map<String, String> response = new java.util.HashMap<>();
        try {
            Member member = memberService.getById(id);
            member.setDeleted(true);
            member.setApproved(false);
            member.setNewUser(false);
            memberService.save(member);
            response.put("status", "success");
            response.put("message", "회원이 성공적으로 거절 및 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "회원 거절 및 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Admin API to reset member password
    @PostMapping("/reset-password/{id}")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public ResponseEntity<Map<String, String>> resetMemberPassword(@PathVariable Long id) {
        Map<String, String> response = new java.util.HashMap<>();
        try {
            memberService.resetPassword(id, "password"); // Default password is 'password'
            response.put("status", "success");
            response.put("message", "비밀번호가 성공적으로 초기화되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "비밀번호 초기화 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //join
    @GetMapping("/join")
    public String applyToJoin(){
        return "/member/join";
    }

    // New Members List for Admin
    @GetMapping("/memberList")
    public String getNewMembers(Model model) {
        model.addAttribute("newMembers", memberService.getNewMemberByNewUserTrue());
        return "member/memberList";
    }

    @GetMapping("/settings")
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

    @PostMapping("/settings")
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

    @PostMapping("/change-password")
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

    @GetMapping("/profile")
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

    @PostMapping("/delete")
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