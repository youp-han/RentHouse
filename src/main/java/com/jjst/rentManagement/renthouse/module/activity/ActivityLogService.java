package com.jjst.rentManagement.renthouse.module.activity;

import java.util.List;

public interface ActivityLogService {
    void logActivity(ActivityType type, String description, Long relatedId);

    List<ActivityLog> getRecentActivities();
}
