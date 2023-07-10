package com.shuz.ecommerce.service;

import com.shuz.ecommerce.entity.Order;
import com.shuz.ecommerce.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> generateOrderItems(Order order);
}
