package com.jjst.rentManagement.renthouse.util;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import org.springframework.stereotype.Component;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import jakarta.annotation.PostConstruct;

@Component
public class EntityConverter {
    private final ModelMapper modelMapper = new ModelMapper();

    @PostConstruct
    public void init() {
        modelMapper.addMappings(new PropertyMap<Lease, LeaseDto>() {
            @Override
            protected void configure() {
                map().setUnitNumber(source.getUnit().getUnitNumber());
                map().setPropertyName(source.getUnit().getProperty().getName());
                map().setTenantName(source.getTenant().getName());
                map().setPropertyId(source.getUnit().getProperty().getId());
            }
        });
    }

    //Entity to Dto
    //converter.convertToDto(Tenancy, TenancyDto.class);
    public <D, E> D convertToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    //Dto to Entity
    //converter.convertToEntity(dto, Tenancy.class);
    public <E, D> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

}