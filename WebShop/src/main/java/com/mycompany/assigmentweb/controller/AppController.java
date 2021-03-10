package com.mycompany.assigmentweb.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(ModelMap model) {

        model.addAttribute("loggedinuser", getCurrentUser());

        return "home";
    }
    
    @RequestMapping(value = {"/aboutus"}, method = RequestMethod.GET)
    public String aboutUs(ModelMap model) {

        model.addAttribute("loggedinuser", getCurrentUser());

        return "aboutUs";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admmin(ModelMap model) {

        model.addAttribute("loggedinuser", getCurrentUser());

        return "admin";
    }
    
    

  

    private String getCurrentUser() {
        String userName = null;
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser instanceof UserDetails) {
            userName = ((UserDetails) currentUser).getUsername();
        } else {
            userName = currentUser.toString();
        }
        return userName;
    }

}
