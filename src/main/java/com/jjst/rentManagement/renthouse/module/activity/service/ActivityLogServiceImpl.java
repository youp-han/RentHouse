package com.jjst.rentManagement.renthouse.module.activity.service;

import com.jjst.rentManagement.renthouse.module.activity.repository.ActivityLogRepository;
import com.jjst.rentManagement.renthouse.service.ActivityLogService;
import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityLog;
import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Override
    public void logActivity(ActivityType type, String description, Long relatedId) {
        ActivityLog log = ActivityLog.builder()
                .activityType(type)
                .description(description)
                .relatedId(relatedId)
                .build();
        activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLog> getRecentActivities() {
        return activityLogRepository.findTop10ByOrderByCreateTimeDesc();
    }
}
