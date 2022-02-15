package com.mdm.employee.controller;

import com.mdm.employee.entities.user;
import com.mdm.employee.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.regex.Pattern;

@Controller
public class userController {

    @Autowired
    private userRepository repo;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String home(){
        return "index";
    }

    @RequestMapping("/employee")
    public String showEmployees( Model modelMap) {
        return "index";
    }

    public boolean checkPassword(String enteredPassword, String password) {
        return enteredPassword.equals(password);
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
