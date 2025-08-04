package com.jjst.rentManagement.renthouse.module.leases.service;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.leases.repository.LeaseRepository;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.TenantService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EntityConverter entityConverter;

    @Override
    public List<Lease> getAllLeases(){
        return leaseRepository.findAll();
    }

    @Transactional
    @Override
    public Lease registerLease(LeaseDto leaseDto) throws Exception {
        Tenant tenant = tenantService.getTenantById(leaseDto.getTenantId());
        Unit unit = propertyService.getUnitById(leaseDto.getUnitId());

        if (tenant == null || unit == null) {
            throw new Exception("Tenant or Unit not found");
        }

        Lease lease = new Lease();
        lease.setTenant(tenant);
        lease.setUnit(unit);
        lease.setStartDate(leaseDto.getStartDate());
        lease.setEndDate(leaseDto.getEndDate());
        lease.setDeposit(leaseDto.getDeposit());
        lease.setMonthlyRent(leaseDto.getMonthlyRent());
        lease.setContractNotes(leaseDto.getContractNotes());
        lease.setLeaseStatus(leaseDto.getLeaseType());

        return leaseRepository.save(lease);
    }

    void save (Lease lease) throws Exception{
        leaseRepository.save(lease);
    }

}
