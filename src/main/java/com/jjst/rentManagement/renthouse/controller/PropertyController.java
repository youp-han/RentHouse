package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.Properties.entity.UnitAttribute;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    //Register Property
    @GetMapping("/admin/property/register")
    public String registerProperty(){
        return "/property/register";
    }


    @PostMapping("/admin/property/save")
    public String saveProperty(@RequestParam String address) {

        Property property = new Property();
        property.setAddress(address);

        propertyService.saveProperty(property);

        return "redirect:/admin/home";
    }

    //Register Units
    @GetMapping("/admin/property/unit/register")
    public String registerUnit(@RequestParam long propertyId, Model model){
        Property property = propertyService.getPropertyById(propertyId);
        model.addAttribute("property", property);

        return "/property/unit/register";

    }

    @PostMapping("/admin/property/unit/save")
    public String saveUnit(@RequestParam Long propertyId, @RequestParam String unitNumber) {
        Property property = propertyService.getPropertyById(propertyId);
        Unit unit = new Unit();
        unit.setUnitNumber(unitNumber);
        unit.setProperty(property);
        unit.setRentStatus("n");
        property.getUnits().add(unit);
        propertyService.saveProperty(property);
        return "redirect:/admin/home";
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

    @PostMapping("/admin/property/unit/room/save")
    public String saveRoom(
            @RequestParam Long unitId,
            @RequestParam String type,
            @RequestParam double size,
            @RequestParam String features,
            RedirectAttributes redirectAttributes) {

        try {
            // Validate and fetch the unit
            Unit unit = propertyService.getUnitById(unitId);
            if (unit == null || unitId <= 0) {
                throw new IllegalArgumentException("Invalid Unit ID: " + unitId);
            }

            // Validate inputs
            if (features == null || features.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Features cannot be empty.");
                return "redirect:/admin/property/unit/room/register?unitId=" + unitId;
            }
            if (size <= 0) {
                redirectAttributes.addFlashAttribute("error", "Room size must be a positive number.");
                return "redirect:/admin/property/unit/room/register?unitId=" + unitId;
            }

            // Create and populate the new Room object
            UnitAttribute unitAttribute = new UnitAttribute();
            //UnitAttribute setFeatureKey(key)
            //unitAttribute.setFeatureValue(value);
            unitAttribute.setUnit(unit);

            // Add room to unit and save
            unit.getUnitAttributes().add(unitAttribute);
            propertyService.saveUnit(unit);

            // Success message
            redirectAttributes.addFlashAttribute("message",
                    "Room successfully registered: Type " + type + ", Size " + size + " sqm.");
        } catch (Exception e) {
            // Handle unexpected errors
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/admin/property/unit/room/register?unitId=" + unitId;
        }

        return "redirect:/admin/home";
    }


}
