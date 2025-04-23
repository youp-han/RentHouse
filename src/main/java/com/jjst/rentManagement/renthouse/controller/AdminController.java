package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.dto.TenancyDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import com.jjst.rentManagement.renthouse.service.TenancyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TenancyService tenancyService;


    @GetMapping("/admin/home")
    public String adminHome(HttpServletRequest request, Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);

        return "/admin/adminHome";
    }

    @GetMapping("/admin/tenantsList")
    public String tenantsList(Model model){

        List<TenancyDto> tenancyDtos = tenancyService.getAllTenancyDtos();
        model.addAttribute("tenantList", tenancyDtos);

        return "/admin/tenantsList";
    }

    @GetMapping("/admin/newMembers")
    public String applyList(Model model){

        List<Member> memberList = memberService.getNewMemberByIsNewTrue();
        List<Property> propertyList= propertyService.getAllProperties();

        List<PropertyDto> propertyDtoList = new ArrayList<>();

        for (Property item : propertyList){
            PropertyDto propertyDto = new PropertyDto();
            propertyDto.setPropertyId(item.getId());
            propertyDto.setAddress(item.getAddress());
            propertyDto.setNickname(item.getNickName());
            propertyDtoList.add(propertyDto);
        }

        model.addAttribute("memberList", memberList);
        model.addAttribute("propertyList", propertyDtoList);
        return "/admin/applyList";
    }


    //Register Property
    @GetMapping("/admin/property/register")
    public String registerProperty(){
        return "/property/register";
    }

    //Register Units
    @GetMapping("/admin/property/unit/register")
    public String registerUnit(@RequestParam long propertyId, Model model){
        Property property = propertyService.getPropertyById(propertyId);
        model.addAttribute("property", property);

        return "/property/unit/register";

    }

    // Register Rooms
    @GetMapping("/admin/property/unit/room/register")
    public String registerRoom(@RequestParam long unitId, Model model) {
        Unit unit = propertyService.getUnitById(unitId);

        // Error handling for invalid unitId
        if (unit == null) {
            throw new IllegalArgumentException("Invalid Unit ID: " + unitId);
        }
        model.addAttribute("unitId", unitId);
        return "/property/unit/addRoom"; // Ensure this is the correct Freemarker template path
    }

    @GetMapping("/admin/propertyList")
    public String listProperties(Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);

        return "/property/propertyList";
    }


}
