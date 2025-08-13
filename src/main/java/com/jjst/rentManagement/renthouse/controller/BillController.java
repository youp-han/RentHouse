package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.BillDto;
import com.jjst.rentManagement.renthouse.module.common.enums.BillCategory;
import com.jjst.rentManagement.renthouse.service.BillService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
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

    @Autowired
    private EntityConverter entityConverter;

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
    public String createBill(@ModelAttribute BillDto billDto) {
        billService.createBill(billDto);
        return "redirect:/bills";
    }

    @GetMapping("/edit/{id}")
    public String editBillForm(@PathVariable Long id, Model model) throws Exception {
        BillDto billDto = entityConverter.convertToDto(billService.getBillById(id),  BillDto.class);

        model.addAttribute("bill", billDto);
        model.addAttribute("billCategories", BillCategory.values());
        return "bill/register"; // Reusing the register template
    }

    @PostMapping("/update")
    public String updateBill(@ModelAttribute BillDto billDto) {
        billService.updateBill(billDto);
        return "redirect:/bills"; // Redirect to the bill list page
    }

    @PostMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return "redirect:/bills"; // Redirect to the bill list page
    }
}
