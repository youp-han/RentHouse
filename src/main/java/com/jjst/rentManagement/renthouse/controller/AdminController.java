package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.module.bills.repository.BillingRepository;
import com.jjst.rentManagement.renthouse.module.common.enums.LeaseStatus;
import com.jjst.rentManagement.renthouse.module.leases.repository.LeaseRepository;
import com.jjst.rentManagement.renthouse.module.properties.repository.UnitRepository;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class AdminController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private UnitRepository unitRepository;


    @GetMapping("/admin/home")
    public String adminHome(HttpServletRequest request, Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);

        return "/admin/adminHome";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        long totalProperties = propertyService.getAllProperties().size();
        long totalUnits = unitRepository.count();
        long occupiedUnits = leaseRepository.countByLeaseStatus(LeaseStatus.ACTIVE);
        double vacancyRate = 0;
        if (totalUnits > 0) {
            vacancyRate = (double) (totalUnits - occupiedUnits) / totalUnits * 100;
        }

        model.addAttribute("totalProperties", totalProperties);
        model.addAttribute("totalUnits", totalUnits);
        model.addAttribute("vacancyRate", String.format("%.1f", vacancyRate));
        model.addAttribute("occupiedUnits", occupiedUnits);

        long overdueCount = billingRepository.countByPaidIsFalseAndDueDateBefore(LocalDate.now());
        BigDecimal overdueAmount = billingRepository.findOverdueAmount(LocalDate.now());

        model.addAttribute("overdueCount", overdueCount);
        model.addAttribute("overdueAmount", overdueAmount == null ? BigDecimal.ZERO : overdueAmount);

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromNow = today.plusDays(30);
        long expiringLeaseCount = leaseRepository.countByLeaseStatusAndEndDateBetween(LeaseStatus.ACTIVE, today, thirtyDaysFromNow);

        model.addAttribute("expiringLeaseCount", expiringLeaseCount);

        return "admin/dashboard";
    }
}
