package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LeaseService leaseService;


    @GetMapping("/admin/home")
    public String adminHome(HttpServletRequest request, Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);

        return "/admin/adminHome";
    }
}
