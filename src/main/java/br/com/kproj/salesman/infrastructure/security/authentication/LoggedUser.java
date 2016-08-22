package br.com.kproj.salesman.infrastructure.security.authentication;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Boolean.TRUE;



@SuppressWarnings("serial")
public class LoggedUser implements UserDetails {
	
	private String login;
	
	private String password;
	
	private UserEntity user;

	
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	
	@Override
	public String getUsername() {
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return TRUE;
	}
	@Override
	public boolean isAccountNonLocked() {
		return TRUE;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return TRUE;
	}
	@Override
	public boolean isEnabled() {
		return TRUE;
	}
	
	public void addRole(String role) {
		this.authorities.add(new SimpleGrantedAuthority(role));
	}
	
	public void removeRole(String role) {
		this.authorities.remove(new SimpleGrantedAuthority(role));
	}
}
