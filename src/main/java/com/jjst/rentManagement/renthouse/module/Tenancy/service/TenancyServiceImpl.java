package com.jjst.rentManagement.renthouse.module.Tenancy.service;

import com.jjst.rentManagement.renthouse.module.Tenancy.entity.Tenancy;
import com.jjst.rentManagement.renthouse.module.Tenancy.repository.TenancyRepository;
import com.jjst.rentManagement.renthouse.service.TenancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenancyServiceImpl implements TenancyService {

    @Autowired
    private TenancyRepository tenancyRepository;

    @Override
    public void registerTenancy(Tenancy tenancy) throws Exception {
        try{
            tenancyRepository.save(tenancy);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
