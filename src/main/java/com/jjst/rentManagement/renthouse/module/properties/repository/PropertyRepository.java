package com.jjst.rentManagement.renthouse.module.properties.repository;

import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAll();

}
