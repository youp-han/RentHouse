package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.BillDto;
import com.jjst.rentManagement.renthouse.module.common.enums.BillCategory;
import com.jjst.rentManagement.renthouse.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public String getAllBills(Model model) {
        List<BillDto> bills = billService.getAllBills();
        model.addAttribute("bills", bills);
        return "bill/billList";
    }

    @GetMapping("/register")
    public String registerBillForm(Model model) {
        model.addAttribute("billCategories", BillCategory.values());
        return "bill/register";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BillDto> createBill(@RequestBody BillDto billDto) {
        BillDto createdBill = billService.createBill(billDto);
        return ResponseEntity.ok(createdBill);
    }
}
