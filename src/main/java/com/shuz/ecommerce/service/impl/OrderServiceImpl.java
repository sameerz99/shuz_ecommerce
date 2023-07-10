package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.entity.Order;
import com.shuz.ecommerce.repo.OrderRepo;
import com.shuz.ecommerce.repo.ShipmentRepo;
import com.shuz.ecommerce.service.CartService;
import com.shuz.ecommerce.service.OrderService;
import com.shuz.ecommerce.service.other.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CartService cartService;
    @Autowired
    MiscService miscService;
    @Autowired
    ShipmentRepo shipmentRepo;
    @Override
    public Order saveOrderToTable(Integer shipmentId) {
        Order newOrder = new Order();

        //Generating a random string for order
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            char randomChar = (char) ((int) (Math.random() * 94) + 33);
            sb.append(randomChar);
        }



        //Adding it as order no
        newOrder.setOrderNo(sb.toString());

        newOrder.setShipment(shipmentRepo.findById(shipmentId).get());
        newOrder.setUser(miscService.getLoggedInUser());
        newOrder.setStatus("Order Created");
        newOrder.setTotalPrice(cartService.getTotalCartValueOfUser());
        return orderRepo.save(newOrder);
    }

    @Override
    public Order directOrder(Integer id) {
        return null;
    }
}
