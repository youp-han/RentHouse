package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.Leases.entity.Lease;

import java.util.List;

public interface LeaseService {

    void registerLease(Lease tenancy, Member member, Unit unit) throws Exception;
    List<LeaseDto> getAllLeaseDtos();
}
