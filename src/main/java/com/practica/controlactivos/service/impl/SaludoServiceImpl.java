/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.service.impl;

import com.practica.controlactivos.service.SaludoService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Omar
 */
@Service
public class SaludoServiceImpl implements SaludoService{

    @Override
    public Map<String, Object> getInfoUser(String name, String lastname, String title) {
        Map <String, Object> datos = new HashMap<>();
        Map <String, Object> direccion = new HashMap<>();
        
        datos.put("titulo", title);
        datos.put("nombre", name);
        datos.put("paterno", lastname);
        
        datos.put("fecha_nacimiento", LocalDateTime.now());
        
        direccion.put("Calle", "Nueva esperanza");
        direccion.put("Numero", new Long("23"));
        
        datos.put("direccion", direccion);
        return datos;
    }
    
}
