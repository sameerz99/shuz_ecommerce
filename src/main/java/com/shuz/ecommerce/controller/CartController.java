package com.shuz.ecommerce.controller;

import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.dto.Response.WishlistResponseDto;
import com.shuz.ecommerce.dto.request.CartRequestDto;
import com.shuz.ecommerce.dto.request.WishlistRequestDto;
import com.shuz.ecommerce.entity.Wishlist;
import com.shuz.ecommerce.service.CartService;
import com.shuz.ecommerce.service.WishlistService;
import com.shuz.ecommerce.service.other.MiscService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final MiscService miscService;
    private final WishlistService wishlistService;


    //-------------------- Add to cart --------------------------------
    /*
    FOr add to cart function pass the cartDto with fields qunatity, product_id to the cutomer
    then bind those values there and then save them to the able.
    * */
    @PostMapping("/add-to-cart/{product_id}")
    public String addToCartFunction(@ModelAttribute CartRequestDto cartRequestDto, @PathVariable("product_id") Integer product_id){
        //Only allowing to add to cart if authenticated
        if(miscService.isUserLoggedIn()){
            cartRequestDto.setProduct_id(product_id);

            cartService.addProductToCart(cartRequestDto);
            return "redirect:/";
        }else{
            return "redirect:/login";
        }

    }


    //Show the items in the cart -- Cart's Page
    @GetMapping("/my-cart")
    public String getMyCart(Model model, @ModelAttribute("deleteMsg") String deleteMsg){
        Integer total = 0;
        //If the user is logged in
        if(miscService.isUserLoggedIn()){
            //Get all the items in the cart
            List<CartResponseDto> allCartItems = cartService.getAllCartItemsOfUser();


            //Calculating the total
            for(CartResponseDto each: allCartItems){
                total += (each.getProduct().getPrice());
            }


            model.addAttribute("cartItems",allCartItems );

            model.addAttribute("deleteMsg", deleteMsg);
            model.addAttribute("total", total);
            return "cart";
        }else{
            return "redirect:/login";
        }
    }

    //Delete the items in the cart
    //Since user will only see this button if cart's page, which can only be accessed
    //when logged, in no need to check if the user is logged in.
    @GetMapping("/delete-item/{id}")
    public String deleteItemInCart(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        cartService.deleteItemInCart(id);
        redirectAttributes.addAttribute("deleteMsg", "Item deleted.");
        return "redirect:/my-cart";
    }

    //for wishlist
    @PostMapping("/add-to-wishlist/{product_id}")
    public String addToWishlistFunction(@ModelAttribute WishlistRequestDto wishlistRequestDto, @PathVariable("product_id") Integer product_id){
        //Only allowing to add to cart if authenticated
        if(miscService.isUserLoggedIn()){
            wishlistRequestDto.setProduct_id(product_id);

            wishlistService.addProductToWishlist(wishlistRequestDto);
            return "redirect:/";
        }else{
            return "redirect:/login";
        }

    }



    @GetMapping("/my-wishlist")
    public String getMyWishlist(Model model,@ModelAttribute("deleteMsg") String deleteMsg){
        if(miscService.isUserLoggedIn()){
            //Get all the items in the cart
            List<WishlistResponseDto> allWishlistItems = wishlistService.getAllWishlistItemsOfUser();
            model.addAttribute("wishlistItems",allWishlistItems );
            model.addAttribute("deleteMsg", deleteMsg);
            return "wishlist";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/delete-wishlist-item/{id}")
    public String deleteItemInWishlist(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        wishlistService.deleteItemInWishlist(id);
        redirectAttributes.addAttribute("deleteMsg", "Item deleted.");
        return "redirect:/my-wishlist";
    }

}
