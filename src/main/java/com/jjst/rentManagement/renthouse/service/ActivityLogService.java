package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityLog;
import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityType;

import java.util.List;

public interface ActivityLogService {
    void logActivity(ActivityType type, String description, Long relatedId);

    List<ActivityLog> getRecentActivities();
}
