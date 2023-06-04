package com.example.kiemtra.Controller;

    import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.kiemtra.Entity.User;
import com.example.kiemtra.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session, Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            session.setAttribute("infoUser", userDetails);
            if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
                return "redirect:/nhanvien";
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("BacSi"))) {
                return "redirect:/bacsi";
            } else {
                return "redirect:/login";
            }
        }
        model.addAttribute("user", new User());
        return "account/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "account/register";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "account/login";
    }

    
}
