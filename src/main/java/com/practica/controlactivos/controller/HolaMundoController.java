/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.controller;

import com.practica.controlactivos.model.User;
import com.practica.controlactivos.service.SaludoService;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    public void setSaludoService(SaludoService saludoService) {
        this.saludoService = saludoService;
    }
    
    @GetMapping("/hola/{nombre}/{paterno}")
    public Map<String, Object> hola( @PathVariable("nombre") String nombre,
        @PathVariable("paterno") String paterno,
        @RequestParam("titulo") String titulo){
        
        logger.log(Level.FINE, "En el metodo Get del Controlador");
        return saludoService.getInfoUser(nombre, paterno, titulo);
    }
    
    @PostMapping()
    public List<User> createUser(@RequestBody List<User> users){
        
        for(User user: users){
            user.setName(user.getName().toUpperCase());
        }
        
        return users;
    }
}
