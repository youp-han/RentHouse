package com.jjst.rentManagement.renthouse.module.properties.repository;

import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    List<Unit> findByPropertyId(long propertyId);
}
