package com.example.demo.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoUser;



@Service
public class UUserDetailsService implements UserDetailsService {

	@Autowired
	private DaoUser daoUser;



	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findLocalOrFacebookUser(username);
    }

    
    private UserDetails findLocalOrFacebookUser(String username){
    	Optional<com.example.demo.model.User> optional = daoUser.findByUsername(username);
    	
    	if(optional.isPresent()){
    		com.example.demo.model.User theUser = optional.get();
    		
    		
    		
    		UserDetails springSecurityUser = User.withDefaultPasswordEncoder()
	                .username(theUser.getUsername())
	                .password(theUser.getPassword())
	                .authorities(getAuthority())
	                .build();
    	
		
			return springSecurityUser;
    	}

    	else{
    		throw new UsernameNotFoundException("");
    	}
    	
    }
    
   

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.emptyList();
    }
}
