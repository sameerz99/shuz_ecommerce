package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.dto.Response.ShipmentResponseDto;
import com.shuz.ecommerce.dto.request.ShipmentRequestDto;
import com.shuz.ecommerce.entity.Shipment;
import com.shuz.ecommerce.repo.ShipmentRepo;
import com.shuz.ecommerce.service.ShipmentService;
import com.shuz.ecommerce.service.other.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    ShipmentRepo shipmentRepo;
    @Autowired
    MiscService miscService;
    @Override
    public ShipmentResponseDto saveShipmentToTable(ShipmentRequestDto dto) {
        Shipment newShip = new Shipment();
        newShip.setAddress(dto.getAddress());
        newShip.setCity(dto.getCity());
        newShip.setPhone(dto.getPhone());
        newShip.setUser(miscService.getLoggedInUser());
        return new ShipmentResponseDto(shipmentRepo.save(newShip));
    }

    @Override
    public List<ShipmentResponseDto> getAllShipmentDetails() {
        List<ShipmentResponseDto> returnList = new ArrayList<>();

        List<Shipment> allItems = shipmentRepo.findByUser(miscService.getLoggedInUser());

        for(Shipment each: allItems){
            returnList.add(new ShipmentResponseDto(each));
        }
        return returnList;
    }

    @Override
    public ShipmentResponseDto updateShipmentDetails(Integer shipmentId, ShipmentRequestDto shipmentRequestDto) {
        Shipment foundDetail = shipmentRepo.findById(shipmentId).get();

        foundDetail.setAddress(shipmentRequestDto.getAddress());
        foundDetail.setCity(shipmentRequestDto.getCity());
        foundDetail.setPhone(shipmentRequestDto.getPhone());

        return new ShipmentResponseDto(foundDetail);
    }

    @Override
    public void deleteShipmentDetails(Integer shipmentId) {
        shipmentRepo.deleteById(shipmentId);
    }
}
