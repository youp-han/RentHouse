package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.TenancyDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.Tenancy.entity.Tenancy;

import java.util.List;

public interface TenancyService {

    void registerTenancy(Tenancy tenancy, Member member, Unit unit) throws Exception;
    List<TenancyDto> getAllTenancyDtos();
}
