/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.controller;

import com.practica.controlactivos.model.User;
import com.practica.controlactivos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author omar
 */

@RestController
@RequestMapping("/util")
public class UtilController {
    
    UserService userService;
    PasswordEncoder passwordEncoder;
    
    @Autowired
    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("createAdmin")
    public String createUser(){
    
	try{
	    userService.loadUserByUsername("admin");
	}catch(UsernameNotFoundException unfe){
	    User user = new User();
	    user.setUserName("admin");
	    user.setPassword(passwordEncoder.encode("admin1234.."));
	    userService.save(user);
	}
	
	return "exito";
    }
}
