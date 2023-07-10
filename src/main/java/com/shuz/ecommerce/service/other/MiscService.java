package com.shuz.ecommerce.service.other;

import com.shuz.ecommerce.entity.User;
import com.shuz.ecommerce.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MiscService {
    @Autowired
    UserRepo userRepo;

    //Function to check if the user is logged in or not
    //If the user is logged in return true, else return false
    public boolean isUserLoggedIn() {
        // Get the authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        return authentication != null && authentication.isAuthenticated();
    }

    public User getLoggedInUser(){
        //Getting the username of the authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //Finding the user
        return userRepo.getUserByUsername(username);

    }
}
