package com.shuz.ecommerce.controller;

import com.shuz.ecommerce.entity.Order;
import com.shuz.ecommerce.entity.OrderItem;
import com.shuz.ecommerce.service.*;
import com.shuz.ecommerce.service.other.EmailService;
import com.shuz.ecommerce.service.other.MiscService;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductService productService;
    @Autowired
    ShipmentService shipmentService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiscService miscService;
    @Autowired
    EmailService emailService;

    @GetMapping("/set-order-items")
    public String getOrderItems(@ModelAttribute("createdOrder") Order createdOrder, Model model){
        List<OrderItem> orderItems = orderItemService.generateOrderItems(createdOrder);
        model.addAttribute("shipmentDetails", shipmentService.getAllShipmentDetails());
        model.addAttribute("products",productService.getAllProduct());

        //--Send the email to the user
        String subject = "Order Placed Successfully";

        String message = "Dear User, \n" +
                "Your order has been placed successfully." +
                "Your order number is \" " + createdOrder.getOrderNo() + "\" ." +
                "The items you ordered are: \n\n"+
                "Item Name | \tPrice |\tTotal| \n";

        for(OrderItem each: orderItems){
            message += each.getProduct().getName()+" | \t";
            message += each.getPrice()+" | \t";
            message += each.getPrice() + " | \t";
            message += "\n\n";
        }
        System.out.println("Message: \n "+ message);// #Debug
        emailService.sendEmail(miscService.getLoggedInUser().getEmail(), subject, message);
        System.out.println("Order Done");
        System.out.println(miscService.getLoggedInUser().getEmail());
        return "reviewOrder";
    }

    @PostMapping("/create-order")
    public String createOrderForUser(RedirectAttributes redirectAttributes,
                                     @RequestParam("selectedDetails") String[] selectedDetails){

        Order createdOrder = orderService.saveOrderToTable(Integer.valueOf(selectedDetails[0]));
        redirectAttributes.addAttribute("createdOrder", createdOrder);
        return "redirect:/set-order-items";

    }
}
