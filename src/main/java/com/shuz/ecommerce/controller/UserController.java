package com.shuz.ecommerce.controller;

import com.shuz.ecommerce.dto.request.UserRequestDto;
import com.shuz.ecommerce.service.UserService;
import com.shuz.ecommerce.service.other.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

    @GetMapping("/sign-up")
    public String getSignUp(Model model, @ModelAttribute("errorMsg") String errorMsg){
        model.addAttribute("dto", new UserRequestDto());
        model.addAttribute("errorMsg", errorMsg);
        return "register";
    }

    @PostMapping("/sign-up")
    public String signUpData(Model model, RedirectAttributes redirectAttributes, @ModelAttribute UserRequestDto userRequestDto){
        try{
            userService.saveUserToTable(userRequestDto);

//            Sending the email after successful sign up

            String subject = "Shuz";

            String message = "Thank you for signing up.";

            emailService.sendEmail(userRequestDto.getEmail(), subject, message);


        }catch (DataIntegrityViolationException e){
            redirectAttributes.addAttribute("errorMsg", "Username Taken.");
            return "redirect:/sign-up";
        }
        return "redirect:/login";
    }
}
