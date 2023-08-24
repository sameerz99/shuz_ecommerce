package com.shuz.ecommerce.controller;

import com.shuz.ecommerce.dto.CreatePayment;
import com.shuz.ecommerce.dto.CreatePaymentResponse;
import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.service.CartService;
import com.shuz.ecommerce.service.ProductService;
import com.shuz.ecommerce.service.other.MiscService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    MiscService miscService;


    @PostMapping("/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {
            double total = cartService.getTotalCartValueOfUser();
            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency("npr")
                    .setAmount((long)(total * 100L))
                    .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());

    }}



