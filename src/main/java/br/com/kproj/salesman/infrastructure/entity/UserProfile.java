package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile extends Identifiable {

	
	private static final long serialVersionUID = 4177083810167336389L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
