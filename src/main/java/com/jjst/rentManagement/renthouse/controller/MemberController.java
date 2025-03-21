package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.Service.MemberService;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/join")
    public String applyToJoin(){
        return "/member/join";
    }

    @PostMapping("/member/save")
    @ResponseBody
    public ResponseEntity<String> saveMemberApplication(@RequestBody Member member) {
        try {
            memberService.registerMember(member, member.getPassword());
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }
    }

    @GetMapping("/member/check-email")
    public boolean checkEmail(@RequestParam String email){
        email = email.toLowerCase().trim();
        boolean result = memberService.checkEmail(email);
        return result;
    }

}
