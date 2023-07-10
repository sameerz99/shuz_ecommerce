package com.shuz.ecommerce.repo;

import com.shuz.ecommerce.entity.Shipment;
import com.shuz.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepo extends JpaRepository<Shipment, Integer> {
    List<Shipment> findByUser(User user);
}
