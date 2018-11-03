/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.service;

import com.practica.controlactivos.dto.UserDTO;
import com.practica.controlactivos.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Omar
 */
public interface UserService extends UserDetailsService{
    public User save(User user);
    public void delete(Long id);
    public List<User> getUsers();
    public User getUser(Long id);
    public List<UserDTO> getUserList();
}
