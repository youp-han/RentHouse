package com.jjst.rentManagement.renthouse.module.tenants.repository;

import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
