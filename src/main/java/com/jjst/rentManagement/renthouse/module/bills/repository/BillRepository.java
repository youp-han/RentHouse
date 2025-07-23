package com.jjst.rentManagement.renthouse.module.bills.repository;

import com.jjst.rentManagement.renthouse.module.bills.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
