package com.jjst.rentManagement.renthouse.util;

import org.springframework.stereotype.Component;

import org.modelmapper.ModelMapper;

@Component
public class EntityConverter {
    private final ModelMapper modelMapper = new ModelMapper();

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
