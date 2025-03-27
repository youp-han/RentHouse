package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.Tenancy.entity.Tenancy;

public interface TenancyService {

    void registerTenancy(Tenancy tenancy) throws Exception;
}
