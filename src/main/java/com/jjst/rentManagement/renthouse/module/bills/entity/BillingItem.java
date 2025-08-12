package com.jjst.rentManagement.renthouse.module.bills.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "billing_item")
public class BillingItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_id", nullable = false)
    private Billing billing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;
}
