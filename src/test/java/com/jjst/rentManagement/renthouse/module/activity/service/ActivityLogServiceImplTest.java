package com.jjst.rentManagement.renthouse.module.activity.service;

import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityLog;
import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityType;
import com.jjst.rentManagement.renthouse.module.activity.repository.ActivityLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityLogServiceImplTest {

    @Mock
    private ActivityLogRepository activityLogRepository;

    @InjectMocks
    private ActivityLogServiceImpl activityLogService;

    private ActivityLog activityLog;

    @BeforeEach
    void setUp() {
        activityLog = ActivityLog.builder()
                .activityType(ActivityType.NEW_LEASE)
                .description("Test Description")
                .relatedId(1L)
                .build();
    }

    @Test
    void logActivity() {
        when(activityLogRepository.save(any(ActivityLog.class))).thenReturn(activityLog);

        activityLogService.logActivity(ActivityType.NEW_LEASE, "Test Description", 1L);

        verify(activityLogRepository, times(1)).save(any(ActivityLog.class));
    }

    @Test
    void getRecentActivities() {
        when(activityLogRepository.findTop10ByOrderByCreateTimeDesc()).thenReturn(Collections.singletonList(activityLog));

        List<ActivityLog> recentActivities = activityLogService.getRecentActivities();

        assertNotNull(recentActivities);
        assertEquals(1, recentActivities.size());
        assertEquals("Test Description", recentActivities.get(0).getDescription());
        verify(activityLogRepository, times(1)).findTop10ByOrderByCreateTimeDesc();
    }
}
