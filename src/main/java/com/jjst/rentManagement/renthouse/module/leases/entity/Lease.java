package com.jjst.rentManagement.renthouse.module.leases.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import com.jjst.rentManagement.renthouse.module.common.enums.LeaseStatus;
import com.jjst.rentManagement.renthouse.module.common.enums.LeaseType;
import com.jjst.rentManagement.renthouse.module.common.enums.MembershipType;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
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
