package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.User;

public class UserBuilder extends AbstractBuilder<User>  {

	public UserBuilder() {
		this.entity = new User();
	}

	public UserBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public UserBuilder withLogin(String login) {
		this.entity.setLogin(login);
		return this;
	}
	
	public UserBuilder withPassword(String pass) {
		this.entity.setPassword(pass);
		return this;
	}
	
	public static UserBuilder createUser(Long id) {
		return new UserBuilder(id);
	}

	public static UserBuilder createUser() {
		return new UserBuilder();
	}
}
