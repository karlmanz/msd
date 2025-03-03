package my.iium.hr.security;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import my.iium.hr.model.Role;
 

public class MyUserDetails implements UserDetails{
	
	   private MyUser user;
	   
	   @Autowired
	   private MyUserRepository userRepo;
	     
	    public MyUserDetails(MyUser user) {
	        this.user = user;
	    }
	 
	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        Set<Role> roles = user.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	         
	        return authorities;
	    }
	 
	    @Override
	    public String getPassword() {
	        return user.getPassword();
	    }
	 
	    @Override
	    public String getUsername() {
	        return user.getUsername();
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
	        return user.isEnabled();
	    }

	 
}
