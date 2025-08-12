package com.jjst.rentManagement.renthouse.module.bills.repository;

import com.jjst.rentManagement.renthouse.module.bills.entity.BillingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingItemRepository extends JpaRepository<BillingItem, Long> {
}
