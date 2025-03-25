package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
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
    public String adminHome(Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);
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
