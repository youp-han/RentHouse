package com.jjst.rentManagement.renthouse.module.Members.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="tenancy")
public class Tenancy extends BaseEntity {
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public BigDecimal downPayment;
    public BigDecimal monthlyRent;
    public BigDecimal expectedTotalRentAmount;
    public enum membershipType{
        monthly,
        yearly,
        jeonse
    }; //monthly (월세), yearly (연세), jeonse (전세)


}
