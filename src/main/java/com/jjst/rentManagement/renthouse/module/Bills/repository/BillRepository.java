package com.jjst.rentManagement.renthouse.module.Bills.repository;

import com.jjst.rentManagement.renthouse.module.Bills.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
