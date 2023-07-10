package com.shuz.ecommerce.dto.Response;

import com.shuz.ecommerce.entity.Shipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponseDto {
    private Integer id;
    private String address;
    private String city;
    private String phone;

    public ShipmentResponseDto(Shipment shipment){
        this.id= shipment.getId();
        this.address= shipment.getAddress();
        this.city= shipment.getCity();
        this.phone= shipment.getPhone();
    }
}
