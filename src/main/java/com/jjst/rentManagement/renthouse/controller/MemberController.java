package com.jjst.rentManagement.renthouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/member/join")
    public String applyToJoin(){
        return "/member/join";
    }

    @GetMapping("/member/save")
    public String saveMemberApplication(){
        return "/member/join";
    }

}
