package com.epicproportionstour.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epicproportionstour.user.dao.UsersDao;
import com.epicproportionstour.user.model.Role;
import com.epicproportionstour.user.model.Users;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersDao userDao;
		
	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Users user = userDao.findUserByName(username); //our own User model class
		System.out.println("---------------------------"+username);
			
		if(user!=null){
			String password = user.getPassword();
			//additional information on the security object
			boolean enabled = user.isIsactive();
			boolean accountNonExpired = user.isIsactive();
			boolean credentialsNonExpired = user.isIsactive();
			boolean accountNonLocked = user.isIsactive();
			
			//Let's populate user roles
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(Role role : user.getRoles()){
				authorities.add(new GrantedAuthorityImpl(role.getRoleName()));
			}
			
			//Now let's create Spring Security User object
			org.springframework.security.core.userdetails.User securityUser = new 
					org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return securityUser;
		}else{
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

}
