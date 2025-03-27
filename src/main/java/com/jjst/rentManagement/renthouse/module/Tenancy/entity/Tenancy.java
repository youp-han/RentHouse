package com.jjst.rentManagement.renthouse.module.Tenancy.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tenancy")
public class Tenancy extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    private LocalDate startDate;
    private LocalDate endDate;
    private double deposit;
    private double monthlyRent;
    private String contractNotes;
    public enum MembershipType{
        monthly,
        yearly,
        jeonse
    }; //monthly (월세), yearly (연세), jeonse (전세)

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

}
