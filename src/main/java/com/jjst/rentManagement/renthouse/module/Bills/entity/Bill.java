package com.jjst.rentManagement.renthouse.module.Bills.entity;

import com.jjst.rentManagement.renthouse.module.Leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import com.jjst.rentManagement.renthouse.module.common.enums.BillCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Data
@Entity
@Table(name = "bill")
public class Bill extends BaseEntity {

    // 관계
    @ManyToOne
    @JoinColumn(name = "lease_id", nullable = false)
    private Lease lease;

    // 관리비 항목
    @Enumerated(EnumType.STRING)
    private BillCategory category;      // 예: MAINTENANCE, ELECTRICITY, WATER, GAS, PARKING

    private YearMonth period;           // 청구 기간 (예: 2025-07)

    @Column(precision = 15, scale = 2)
    private BigDecimal amount;          // 금액

    private LocalDate issueDate;        // 청구일
    private LocalDate dueDate;          // 납부 기한

    private Boolean paid;               // 납부 여부
    private LocalDate paidDate;         // 실제 납부일

}
