package com.jjst.rentManagement.renthouse.module.bills.entity;

import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "billing")
public class Billing extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lease_id", nullable = false)
    private Lease lease;

    @OneToMany(mappedBy = "billing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillingItem> items = new ArrayList<>();

    private YearMonth period;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalAmount;

    private LocalDate issueDate;
    private LocalDate dueDate;

    private Boolean paid;
    private LocalDate paidDate;

    public void updateTotalAmount() {
        this.totalAmount = items.stream()
                                .map(BillingItem::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}