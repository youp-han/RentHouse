package com.jjst.rentManagement.renthouse.module.leases.service;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.leases.repository.LeaseRepository;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import com.jjst.rentManagement.renthouse.service.MemberService;
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
    private MemberService memberService;

    @Override
    public List<LeaseDto> getAllLeaseDtos(){

        List<Lease> leases= leaseRepository.findAll();
        List<LeaseDto> leaseDtos = new ArrayList<>();
        EntityConverter converter = new EntityConverter();

        for(Lease lease: leases){
            leaseDtos.add(converter.convertToDto(lease, LeaseDto.class));
        }
        return leaseDtos;

    }

    @Transactional(rollbackFor = Exception.class)
    public void registerLease(Lease lease, Member member, Unit unit) throws Exception {
        propertyService.saveUnit(unit);
        memberService.save(member);
        save(lease);
    }

    void save (Lease lease) throws Exception{
        leaseRepository.save(lease);
    }

}
