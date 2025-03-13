package com.jjst.rentManagement.renthouse.module.Properties.repository;

import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
