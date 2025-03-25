package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/admin/home")
    public String adminHome(HttpServletRequest request, Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);


        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("Session ID: ===========" + session.getId());
            System.out.println("SPRING_SECURITY_CONTEXT: ================" + session.getAttribute("SPRING_SECURITY_CONTEXT"));
        } else {
            System.out.println("No session found=======================================");
        }


        return "/admin/adminHome";
    }

    @GetMapping("/admin/tenantsList")
    public String tenantsList(){

        return "/admin/tenantsList";
    }

    @PostMapping("/admin/newMembers")
    public String approveMembership(){

        return "/admin/applyList";
    }


}
