package com.jjst.rentManagement.renthouse.module.bills.repository;

import com.jjst.rentManagement.renthouse.module.bills.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface BillingRepository extends JpaxRepository<Billing, Long> {

    long countByPaidIsFalseAndDueDateBefore(LocalDate today);

    @Query("SELECT SUM(b.totalAmount) FROM Billing b WHERE b.paid = false AND b.dueDate < :today")
    BigDecimal findOverdueAmount(@Param("today") LocalDate today);
}
