package com.jjst.rentManagement.renthouse.restController;

import com.jjst.rentManagement.renthouse.dto.MemberDto;
import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.dto.TenantDto;
import com.jjst.rentManagement.renthouse.dto.UnitDto;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.TenantService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EntityConverter entityConverter;

    @PostMapping("/property")
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto propertyDto) {
        try {
            Property property = propertyService.saveProperty(propertyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityConverter.convertToDto(property, PropertyDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/unit")
    public ResponseEntity<UnitDto> createUnit(@RequestBody UnitDto unitDto) {
        try {
            Unit unit = propertyService.saveUnit(unitDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityConverter.convertToDto(unit, UnitDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/units")
    public ResponseEntity<List<UnitDto>> createUnits(@RequestBody List<UnitDto> dtos) {       // List로 변경
        List<UnitDto> saved = new ArrayList<>();
        try{
            for (UnitDto dto : dtos) {
                // 하나씩 저장
                Unit unit = propertyService.saveUnit(dto);
                saved.add(entityConverter.convertToDto(unit, UnitDto.class));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/member")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        try {
            Member member = memberService.save(entityConverter.convertToEntity(memberDto, Member.class));
            return ResponseEntity.status(HttpStatus.CREATED).body(entityConverter.convertToDto(member, MemberDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/members")
    public ResponseEntity<List<MemberDto>> createMembers(@RequestBody List<MemberDto> memberDtos) {
        List<MemberDto> saved = new ArrayList<>();
        try {
            for (MemberDto dto : memberDtos){
                Member member = memberService.save(entityConverter.convertToEntity(dto, Member.class));
                saved.add(dto);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/tenant")
    public ResponseEntity<TenantDto> createTenant(@RequestBody TenantDto tenantDto) {
        try {
            Tenant tenant = tenantService.saveTenant(entityConverter.convertToEntity(tenantDto, Tenant.class));
            return ResponseEntity.status(HttpStatus.CREATED).body(entityConverter.convertToDto(tenant, TenantDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/tenants")
    public ResponseEntity<List<TenantDto>> createTenants(@RequestBody List<TenantDto> tenantDtos) {
        List<TenantDto> saved = new ArrayList<>();
        try {
            for (TenantDto dto : tenantDtos){
                Tenant tenantDto = tenantService.saveTenant(entityConverter.convertToEntity(dto, Tenant.class));
                saved.add(dto);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
