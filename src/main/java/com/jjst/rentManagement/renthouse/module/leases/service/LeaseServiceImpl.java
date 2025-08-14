package com.jjst.rentManagement.renthouse.module.leases.service;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.leases.repository.LeaseRepository;
import com.jjst.rentManagement.renthouse.service.BillingService;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.TenantService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.jjst.rentManagement.renthouse.service.ActivityLogService;
import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaseServiceImpl implements LeaseService {

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private BillingService billingService;

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

        if (unit.getRentStatus()) {
            throw new Exception("Unit is already rented");
        }

        // Update tenant's address
        Property property = unit.getProperty();
        String newAddress = property.getAddress() + ", " + unit.getUnitNumber();
        tenant.setCurrentAddress(newAddress);
        tenantService.saveTenant(tenant);

        // Update unit's rent status
        unit.setRentStatus(true);
        propertyService.saveUnit(unit);

        Lease lease = new Lease();
        lease.setTenant(tenant);
        lease.setUnit(unit);
        lease.setStartDate(Utility.formatStringToLocalDate(leaseDto.getStartDate()));
        lease.setEndDate(Utility.formatStringToLocalDate(leaseDto.getEndDate()));
        lease.setDeposit(leaseDto.getDeposit());
        lease.setMonthlyRent(leaseDto.getMonthlyRent());
        lease.setContractNotes(leaseDto.getContractNotes());
        lease.setLeaseType(leaseDto.getLeaseType());
        lease.setLeaseStatus(com.jjst.rentManagement.renthouse.module.common.enums.LeaseStatus.PENDING);

        Lease savedLease = leaseRepository.save(lease);

        String description = String.format("%s, '%s %s호' 신규 계약",
                savedLease.getTenant().getName(),
                savedLease.getUnit().getProperty().getName(),
                savedLease.getUnit().getUnitNumber());
        activityLogService.logActivity(ActivityType.NEW_LEASE, description, savedLease.getId());

        billingService.generateMonthlyBillingsForLease(savedLease);

        return savedLease;
    }

    void save (Lease lease) throws Exception{
        leaseRepository.save(lease);
    }

    @Override
    public Lease getLeaseById(Long id) throws Exception {
        return leaseRepository.findById(id).orElseThrow(() -> new Exception("Lease not found"));
    }

    @Transactional
    @Override
    public Lease updateLease(Long id, LeaseDto leaseDto) throws Exception {
        Lease existingLease = leaseRepository.findById(id).orElseThrow(() -> new Exception("Lease not found"));

        existingLease.setStartDate(Utility.formatStringToLocalDate(leaseDto.getStartDate()));
        existingLease.setEndDate(Utility.formatStringToLocalDate(leaseDto.getEndDate()));
        existingLease.setDeposit(leaseDto.getDeposit());
        existingLease.setMonthlyRent(leaseDto.getMonthlyRent());
        existingLease.setContractNotes(leaseDto.getContractNotes());
        existingLease.setLeaseStatus(leaseDto.getLeaseStatus());
        return leaseRepository.save(existingLease);
    }

    @Override
    public void deleteLease(Long id) throws Exception {
        if (!leaseRepository.existsById(id)) {
            throw new Exception("Lease not found");
        }
        leaseRepository.deleteById(id);
    }

}