package com.jjst.rentManagement.renthouse.module.Leases.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import com.jjst.rentManagement.renthouse.module.common.entity.LeaseStatus;
import com.jjst.rentManagement.renthouse.module.common.entity.LeaseType;
import com.jjst.rentManagement.renthouse.module.common.entity.MembershipType;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name="lease")
public class Lease extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal deposit;
    private BigDecimal monthlyRent;

    private String contractNotes;

    @Enumerated(EnumType.STRING)
    public LeaseType LeaseStatus;

    @Enumerated(EnumType.STRING)
    private LeaseStatus status;         // ACTIVE, TERMINATED, EXPIRED

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

}
