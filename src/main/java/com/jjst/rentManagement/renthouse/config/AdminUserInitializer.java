package com.jjst.rentManagement.renthouse.config;

import com.jjst.rentManagement.renthouse.module.common.enums.RoleType;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
        // Check if an admin user already exists
        Member adminUser = memberService.getMemberByEmail("admin@renthouse.com");

        if (adminUser == null) {
            // Create a new admin user if not found
            Member newAdmin = new Member();
            newAdmin.setName("Admin");
            newAdmin.setEmail("admin@renthouse.com");
            newAdmin.setRole(RoleType.ADMIN);
            newAdmin.setApproved(true);
            newAdmin.setNewUser(false);
            newAdmin.setDeleted(false);
            // Set a default password (it will be encoded by MemberService.registerMember)
            memberService.registerMember(newAdmin, "adminpass");
            System.out.println("Default admin user created: admin@renthouse.com");
        }
    }
}
