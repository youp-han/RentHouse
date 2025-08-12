package com.jjst.rentManagement.renthouse.module.bills.service;

import com.jjst.rentManagement.renthouse.dto.BillDto;
import com.jjst.rentManagement.renthouse.module.bills.entity.Bill;
import com.jjst.rentManagement.renthouse.module.bills.repository.BillRepository;
import com.jjst.rentManagement.renthouse.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<BillDto> getAllBills() {
        return billRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillDto createBill(BillDto billDto) {
        Bill bill = new Bill();
        bill.setName(billDto.getName());
        bill.setCategory(billDto.getCategory());
        bill.setAmount(billDto.getAmount());
        bill.setDescription(billDto.getDescription());

        Bill savedBill = billRepository.save(bill);
        return convertToDto(savedBill);
    }

    public BillDto convertToDto(Bill bill) {
        BillDto dto = new BillDto();
        dto.setId(bill.getId());
        dto.setName(bill.getName());
        dto.setCategory(bill.getCategory());
        dto.setAmount(bill.getAmount());
        dto.setDescription(bill.getDescription());
        return dto;
    }

    @Override
    public Bill getBillById(Long id) throws Exception {
        return billRepository.findById(id).orElseThrow(() -> new Exception("Bill not found for ID: " + id));
    }

    @Override
    public List<Bill> getAllBillEntities() {
        return billRepository.findAll();
    }
}