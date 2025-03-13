package com.jjst.rentManagement.renthouse.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import jakarta.persistence.*;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedBy //초기 입력
    private String createdBy;

    @CreatedDate
    private LocalDateTime createTime = LocalDateTime.parse(LocalDateTime.now().toString());

    @LastModifiedBy //수정
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate = LocalDateTime.parse(LocalDateTime.now().toString());;

}
