package com.jjst.rentManagement.renthouse.module.Properties.repository;

import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
