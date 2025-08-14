package com.jjst.rentManagement.renthouse.module.activity.repository;

import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findTop10ByOrderByCreateTimeDesc();
}
