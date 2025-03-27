package com.jjst.rentManagement.renthouse.module.Tenancy.repository;

import com.jjst.rentManagement.renthouse.module.Tenancy.entity.Tenancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TenancyRepository extends JpaRepository<Tenancy, Long> {
    List<Tenancy> findAllByEndDate(LocalDate endDate);
}
