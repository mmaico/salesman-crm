package br.com.kproj.salesman.infrastructure.security.helpers;

import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;




@Component
public class SecurityHelper {


	public LoggedUser getPrincipal() {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if (context == null || context.getAuthentication() == null) {
			return null;
		}
		LoggedUser user = (LoggedUser) context.getAuthentication().getDetails();
		
		return user;
	}
	
	public Boolean hasRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null) {
			return Boolean.FALSE;
		}
		
		LoggedUser user = (LoggedUser) authentication.getDetails();
		
		return user.getAuthorities().contains(new SimpleGrantedAuthority(role));
		
	}
	
	public void removeRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		LoggedUser user = (LoggedUser) authentication.getDetails();
		
		user.removeRole(role);
	}
	
}
