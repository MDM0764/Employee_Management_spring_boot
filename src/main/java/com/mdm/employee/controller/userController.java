package com.mdm.employee.controller;

import com.mdm.employee.entities.user;
import com.mdm.employee.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

@Controller
public class userController {

    @Autowired
    private userRepository repo;

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/employee")
    public String showEmployees( Model modelMap) {
        return "index";
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "Login", method = RequestMethod.POST)
    public String login (@RequestParam("userId") String userId, @RequestParam("password") String password, Model modelMap){
        user user;
        try {
            if (isValid(userId)) {//find by email
                user = repo.findByEmail(userId);
            } else {
                Long phoneNo = Long.parseLong(userId);
                user = repo.findByPhoneno(phoneNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("msg", "The User does not exist");
            return "login";
        }

        if (user == null) {
            modelMap.addAttribute("msg", "The User does not exist");
            return "login";
        }
        if (checkPassword(password, user.getPassword())) {
            return "index";
        } else {
            modelMap.addAttribute("msg", "Wrong Passoword");
            return "login";
        }

    }

   /* public boolean checkPassword(String enteredPassword, String password) {
        return bCryptPasswordEncoder().matches(enteredPassword, password);
    }*/

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
