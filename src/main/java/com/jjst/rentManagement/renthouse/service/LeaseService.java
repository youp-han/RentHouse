package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;

import java.util.List;

public interface LeaseService {

    Lease registerLease(LeaseDto leaseDto) throws Exception;
    List<Lease> getAllLeases();
    Lease getLeaseById(Long id) throws Exception;
    Lease updateLease(Long id, LeaseDto leaseDto) throws Exception;
    void deleteLease(Long id) throws Exception;
}
