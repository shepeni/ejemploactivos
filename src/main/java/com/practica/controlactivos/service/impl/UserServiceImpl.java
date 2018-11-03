/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practica.controlactivos.service.impl;

import com.practica.controlactivos.dto.UserDTO;
import com.practica.controlactivos.model.User;
import com.practica.controlactivos.service.UserService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Omar
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public User save(User user) {
        Session session = entityManager.unwrap(Session.class);
        Long id = (Long) session.save(user);
        return this.getUser(id) ;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public List<User> getUsers() {
        Session session = entityManager.unwrap(Session.class);
        Criteria crit = session.createCriteria(User.class);
        crit.createAlias("place", "place", JoinType.LEFT_OUTER_JOIN);
        return crit.list();
    }

    @Override
    public User getUser(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("id", id));
        User user = (User) crit.uniqueResult();
        return user ;
    }

    @Override
    public List<UserDTO> getUserList() {
        Session session = entityManager.unwrap(Session.class);
        
        
        Criteria crit = session.createCriteria(User.class);
        crit.createAlias("place", "place");
        
        
        ProjectionList proyecciones = Projections.projectionList();
        proyecciones.add(Projections.property("name").as("name"));
        proyecciones.add(Projections.property("lastName").as("lastName"));
        proyecciones.add(Projections.property("place.name").as("placeName"));
        
        
        crit.setProjection(proyecciones);
        crit.setResultTransformer(new AliasToBeanResultTransformer(UserDTO.class));
        
        return crit.list();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	Session session = entityManager.unwrap(Session.class);
        
        
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("userName", userName));
	crit.createAlias("roles", "roles", JoinType.LEFT_OUTER_JOIN);
	List<UserDetails> results =  crit.list();
	if(results.size()==0){
	    throw new UsernameNotFoundException("The user doesn't exists");
	}
	return results.get(0);
    }

   
    
}
