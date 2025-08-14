package com.jjst.rentManagement.renthouse.module.activity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity_log")
public class ActivityLog extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;

    @Column(nullable = false)
    private String description;

    private Long relatedId;

}
