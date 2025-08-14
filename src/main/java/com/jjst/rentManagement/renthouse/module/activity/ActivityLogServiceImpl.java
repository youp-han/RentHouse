package com.jjst.rentManagement.renthouse.module.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

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
        return activityLogRepository.findTop10ByOrderByCreatedAtDesc();
    }
}
