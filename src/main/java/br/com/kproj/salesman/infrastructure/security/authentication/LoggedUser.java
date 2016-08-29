package br.com.kproj.salesman.infrastructure.security.authentication;


import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static java.lang.Boolean.TRUE;



@SuppressWarnings("serial")
public class LoggedUser implements UserDetails {

	private Long id;

	private String name;

	private String login;
	
	private String password;

	private List<GrantedAuthority> authorities = Lists.newArrayList();
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public LoggedUser getLoggedUser() {
		return this;
	}
}
