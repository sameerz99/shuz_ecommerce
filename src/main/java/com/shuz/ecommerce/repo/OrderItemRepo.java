package com.shuz.ecommerce.repo;

import com.shuz.ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,Integer> {
}
