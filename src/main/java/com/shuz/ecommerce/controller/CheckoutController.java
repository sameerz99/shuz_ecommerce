package com.shuz.ecommerce.controller;

import com.shuz.ecommerce.dto.request.ShipmentRequestDto;
import com.shuz.ecommerce.service.CartService;
import com.shuz.ecommerce.service.ShipmentService;
import com.shuz.ecommerce.service.other.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {
    @Autowired
    ShipmentService shipmentService;
    @Autowired
    CartService cartService;
    @Autowired
    MiscService miscService;

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model){
        if(miscService.isUserLoggedIn()){
            model.addAttribute("shipmentDetails", shipmentService.getAllShipmentDetails());
            return "checkout";
        }else{
            return "redirect:/login";
        }
    }
    @GetMapping("/get-add-address-form")
    public String getAddAddressForm(Model model){
        model.addAttribute("detail", new ShipmentRequestDto());
        return "addressDetailForm";

    }

    @PostMapping("/get-add-address-form")
    public String saveData(@ModelAttribute ShipmentRequestDto dto){
        shipmentService.saveShipmentToTable(dto);
        return "redirect:/checkout";
    }

    //Delete the shipment address
//    Place this delete somewhere like in add form
    @GetMapping("/delete-shipping-address/{id}")
    public String deleteTheAddress(@PathVariable("id") Integer id){
        shipmentService.deleteShipmentDetails(id);
        return "redirect:/checkout";
    }



}
