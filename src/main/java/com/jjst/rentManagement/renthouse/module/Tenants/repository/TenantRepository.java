package com.jjst.rentManagement.renthouse.module.Tenants.repository;

import com.jjst.rentManagement.renthouse.module.Tenants.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
