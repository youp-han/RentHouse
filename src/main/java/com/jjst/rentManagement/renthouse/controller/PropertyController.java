package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    //Register Property
    @GetMapping("/property/register")
    public String registerProperty(){
        return "/property/register";
    }

    //Register Units
    @GetMapping("/property/unit/register")
    public String registerUnit(@RequestParam long propertyId, Model model){
        Property property = propertyService.getPropertyById(propertyId);
        model.addAttribute("property", property);

        return "/property/unit/register";

    }

    // Register Rooms
    @GetMapping("/property/unit/room/register")
    public String registerRoom(@RequestParam long unitId, Model model) {
        Unit unit = propertyService.getUnitById(unitId);

        // Error handling for invalid unitId
        if (unit == null) {
            throw new IllegalArgumentException("Invalid Unit ID: " + unitId);
        }
        model.addAttribute("unitId", unitId);
        return "/property/unit/addRoom"; // Ensure this is the correct Freemarker template path
    }

    @GetMapping("/propertyList")
    public String listProperties(Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);

        return "/property/propertyList";
    }

    @GetMapping("/property/detail/{id}")
    public String propertyDetail(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "property/detail";
        } else {
            return "redirect:/admin/propertyList"; // Or handle error
        }
    }

    @GetMapping("/property/unit/detail/{id}")
    public String unitDetail(@PathVariable Long id, Model model) {
        Unit unit = propertyService.getUnitById(id);
        if (unit != null) {
            model.addAttribute("unit", unit);
            return "property/unit/detail";
        } else {
            return "redirect:/admin/propertyList"; // Or handle error
        }
    }

}