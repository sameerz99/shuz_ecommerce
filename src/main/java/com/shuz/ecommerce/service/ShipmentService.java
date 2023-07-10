package com.shuz.ecommerce.service;

import com.shuz.ecommerce.dto.Response.ShipmentResponseDto;
import com.shuz.ecommerce.dto.request.ShipmentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ShipmentService {
    //Creating the shipment details
    ShipmentResponseDto saveShipmentToTable(ShipmentRequestDto dto);

    //GetAllShipment
    List<ShipmentResponseDto> getAllShipmentDetails();

    //Update the shipment
    ShipmentResponseDto updateShipmentDetails(Integer shipmentId, ShipmentRequestDto shipmentRequestDto);

    //Delete the shipment
    void deleteShipmentDetails(Integer shipmentId);
}
