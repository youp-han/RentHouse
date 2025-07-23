package com.jjst.rentManagement.renthouse.module.leases.repository;

import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {
    List<Lease> findAllByEndDate(LocalDate endDate);
    List<Lease> findAll();
}
