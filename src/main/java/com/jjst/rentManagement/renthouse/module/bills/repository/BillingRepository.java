package com.jjst.rentManagement.renthouse.module.bills.repository;

import com.jjst.rentManagement.renthouse.module.bills.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
}
