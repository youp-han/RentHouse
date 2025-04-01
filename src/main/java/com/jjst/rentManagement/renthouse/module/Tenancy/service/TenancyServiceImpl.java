package com.jjst.rentManagement.renthouse.module.Tenancy.service;

import com.jjst.rentManagement.renthouse.dto.TenancyDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.Tenancy.entity.Tenancy;
import com.jjst.rentManagement.renthouse.module.Tenancy.repository.TenancyRepository;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.TenancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenancyServiceImpl implements TenancyService {

    @Autowired
    private TenancyRepository tenancyRepository;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MemberService memberService;

    @Override
    public List<TenancyDto> getAllTenancyDtos(){

        List<Tenancy> tenancies= tenancyRepository.findAll();
        List<TenancyDto> tenancyDtos = new ArrayList<>();
        EntityConverter converter = new EntityConverter();

        for(Tenancy tenancy: tenancies){
            tenancyDtos.add(converter.convertToDto(tenancy, TenancyDto.class));
        }
        return tenancyDtos;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerTenancy(Tenancy tenancy, Member member, Unit unit) throws Exception {
        propertyService.saveUnit(unit);
        memberService.save(member);
        save(tenancy);
    }

    void save (Tenancy tenancy) throws Exception{
        tenancyRepository.save(tenancy);
    }

}
