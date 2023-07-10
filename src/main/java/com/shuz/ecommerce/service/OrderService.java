package com.shuz.ecommerce.service;

import com.shuz.ecommerce.entity.Order;

public interface OrderService {
    Order saveOrderToTable(Integer shipmentId);

    Order directOrder(Integer id);
}
