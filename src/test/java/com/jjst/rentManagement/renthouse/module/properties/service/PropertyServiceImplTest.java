package com.jjst.rentManagement.renthouse.module.properties.service;

import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.repository.PropertyRepository;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private EntityConverter entityConverter;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    private Property property;
    private PropertyDto propertyDto;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setId(1L);
        property.setName("Test Property");

        propertyDto = new PropertyDto();
        propertyDto.setPropertyId(1L);
        propertyDto.setName("Test Property DTO");
    }

    @Test
    void getAllProperties() {
        when(propertyRepository.findAll()).thenReturn(Collections.singletonList(property));

        List<Property> properties = propertyService.getAllProperties();

        assertNotNull(properties);
        assertEquals(1, properties.size());
        assertEquals("Test Property", properties.get(0).getName());
        verify(propertyRepository, times(1)).findAll();
    }

    @Test
    void getPropertyById() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));

        Property foundProperty = propertyService.getPropertyById(1L);

        assertNotNull(foundProperty);
        assertEquals("Test Property", foundProperty.getName());
        verify(propertyRepository, times(1)).findById(1L);
    }

    @Test
    void getPropertyById_NotFound() {
        when(propertyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            propertyService.getPropertyById(1L);
        });

        verify(propertyRepository, times(1)).findById(1L);
    }


    @Test
    void savePropertyDto() {
        when(entityConverter.convertToEntity(any(PropertyDto.class), eq(Property.class))).thenReturn(property);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property savedProperty = propertyService.saveProperty(propertyDto);

        assertNotNull(savedProperty);
        assertEquals("Test Property", savedProperty.getName());
        verify(entityConverter, times(1)).convertToEntity(any(PropertyDto.class), eq(Property.class));
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void updateProperty() throws Exception {
        Property updatedInfo = new Property();
        updatedInfo.setId(1L);
        updatedInfo.setName("Updated Name");

        when(propertyRepository.findById(1L)).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(updatedInfo);

        propertyService.updateProperty(updatedInfo);

        verify(propertyRepository, times(1)).findById(1L);
        verify(propertyRepository, times(1)).save(property);
        assertEquals("Updated Name", property.getName());
    }

    @Test
    void deleteProperty() throws Exception {
        when(propertyRepository.existsById(1L)).thenReturn(true);
        doNothing().when(propertyRepository).deleteById(1L);

        propertyService.deleteProperty(1L);

        verify(propertyRepository, times(1)).existsById(1L);
        verify(propertyRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProperty_NotFound() {
        when(propertyRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            propertyService.deleteProperty(1L);
        });

        verify(propertyRepository, times(1)).existsById(1L);
        verify(propertyRepository, never()).deleteById(1L);
    }
}
