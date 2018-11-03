/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Omar
 */ 

@Entity
@Table(name="usr_user", indexes = {
    @Index(columnList= "name", name = "IDX_name"),
    @Index(columnList= "lastname")
})
public class User implements UserDetails{
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id; 
    
   private String name;
   private String lastName;
   private LocalDateTime birthday;
   private String userName;
   private String password;
   
   @Column(length = 100)
   private String email;
   
   @ManyToOne(fetch = FetchType.LAZY)
   private Place place;
   
   @ManyToMany
    @JoinTable(name = "usr_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
   private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Set<Role> getRoles() {
	return roles;
    }

    public void setRoles(Set<Role> roles) {
	this.roles = roles;
    }
    
    

    @Override
    public Collection<Role> getAuthorities() {
	return roles;
    }

    @Override
    public String getUsername() {
	return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;	
    }

     public void addRole(Role role) {
        roles.add(role);
        
    }
 
    public void removeRole(Role role) {
        roles.remove(role);
       
    }
    
}
