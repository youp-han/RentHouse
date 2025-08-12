package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/billings")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public String getAllBillings(Model model) {
        List<BillingDto> billings = billingService.getAllBillings();
        model.addAttribute("billings", billings);
        return "billing/billingList";
    }
}
