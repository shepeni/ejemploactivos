/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.service;

import java.util.Map;

/**
 *
 * @author Omar
 */
public interface SaludoService {
    public Map<String, Object> getInfoUser(String name, String lastname, String title);
}
