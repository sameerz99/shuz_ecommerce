package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.entity.Order;
import com.shuz.ecommerce.entity.OrderItem;
import com.shuz.ecommerce.repo.OrderItemRepo;
import com.shuz.ecommerce.service.CartService;
import com.shuz.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    CartService cartService;
    @Autowired
    OrderItemRepo orderItemRepo;
    @Override
    public List<OrderItem> generateOrderItems(Order order) {
        List<OrderItem> returnList = new ArrayList<>();

//        Getting all the items in the cart
        List<CartResponseDto> foundCartItems = cartService.getAllCartItemsOfUser();

        //Save each item in the cart to the order
        for(CartResponseDto each: foundCartItems){
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setPrice(each.getProduct().getPrice());
            newOrderItem.setProduct(each.getProduct());
            newOrderItem.setOrder(order);
            //Save the item to Order
            returnList.add(orderItemRepo.save(newOrderItem));
            //Then delete it from cart
            cartService.deleteItemInCart(each.getId());
        }
        return returnList;
    }
}
