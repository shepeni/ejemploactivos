/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.controller;

import com.practica.controlactivos.dto.UserDTO;
import com.practica.controlactivos.model.User;
import com.practica.controlactivos.service.SaludoService;
import com.practica.controlactivos.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Omar
 */

@RestController
@RequestMapping("/api/user")
public class HolaMundoController {
    
    Logger logger = Logger.getLogger(HolaMundoController.class.getName());
    
    private SaludoService saludoService;
    private UserService userService;

    @Autowired
    public void setSaludoService(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    
    
    @GetMapping("")
    public List<User> hola(){
        
        logger.log(Level.FINE, "En el metodo Get del Controlador");
        return userService.getUsers();
    }
    
    @GetMapping("/resume")
    public List<UserDTO> getUsersResume(){
        
        logger.log(Level.FINE, "En el metodo Get del Controlador");
        return userService.getUserList();
    }
    
    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long  id){
        
        logger.log(Level.FINE, "En el metodo Get del Controlador");
        User user = (User) userService.getUser(id);
        return user;
    }
    
    @PostMapping()
    public List<User> createUser(@RequestBody List<User> users)throws Exception{
        
        for(User user: users){
            userService.save(user);
        }
        return users;
    }
    
    @DeleteMapping("{id}")
    public String deleteObject(@PathVariable("id") Long id){
        userService.delete(id);
        return "Objeto eliminado";
    }
	
	@PostMapping("imagen")
	public String saveImage(@RequestBody UserDTO user){
		
		try {
			byte[] imagen = Base64.getDecoder().decode(user.getImage());
			Path destinationFile = Paths.get("/imagenesEjercicio", "myImage.jpg");
			Files.createDirectories(destinationFile.getParent());
			Files.write(destinationFile, imagen);
		} catch (IOException ex) {
			Logger.getLogger(HolaMundoController.class.getName()).log(Level.SEVERE, null, ex);
			return "No guardado";
		}
		
		return "Guardado";
		
	}
}
