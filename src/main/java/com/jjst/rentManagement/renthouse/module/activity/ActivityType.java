package com.jjst.rentManagement.renthouse.module.activity;

public enum ActivityType {
    NEW_LEASE("신규 계약"),
    RENT_PAID("임대료 납부"),
    NEW_PROPERTY("신규 부동산 등록"),
    NEW_TENANT("신규 임차인 등록"),
    NEW_MEMBER("신규 회원 가입");

    private final String description;

    ActivityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
