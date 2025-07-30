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
import java.util.stream.Collectors;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private EntityConverter entityConverter;

    @GetMapping("/admin/newMembers")
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

    @GetMapping("/admin/members")
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAll();
        List<MemberDto> memberDtos = members.stream()
                .map(member -> entityConverter.convertToDto(member, MemberDto.class))
                .collect(Collectors.toList());
        model.addAttribute("members", memberDtos);
        return "admin/memberList";
    }

    @PostMapping("/member/authenticate")
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

    @PostMapping("/member/save")
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

    @GetMapping("/member/check-email")
    @ResponseBody // Keep @ResponseBody for AJAX call
    public boolean checkEmail(@RequestParam String email){
        email = email.toLowerCase().trim();
        boolean result = memberService.checkEmail(email);
        return result;
    }

    // Admin API to get member by ID
    @GetMapping("/admin/member/{id}")
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
    @PutMapping("/admin/member/{id}")
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
    @PostMapping("/admin/member/approve/{id}")
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
    @PostMapping("/admin/member/reject/{id}")
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
    @PostMapping("/admin/member/reset-password/{id}")
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

}