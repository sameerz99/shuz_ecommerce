package com.shuz.ecommerce;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ECommerceApplication {
	@PostConstruct
	public void setup(){
		Stripe.apiKey="sk_test_51NTjncGW6yWwDXYAfav7LK3VjVGNJJWLsRzk5ruDy3goWyBeLE9Jwa8MQa78GKzmcBd26rmfL0cXtwX0BsBf23SG004Ckz9BG4";
	}
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
