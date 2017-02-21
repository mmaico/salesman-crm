package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;

public class UserEntityBuilder extends AbstractBuilder<UserEntity>  {

	public UserEntityBuilder() {
		this.entity = new UserEntity();
	}

	public UserEntityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public UserEntityBuilder withLogin(String login) {
		this.entity.setLogin(login);
		return this;
	}
	
	public UserEntityBuilder withPassword(String pass) {
		this.entity.setPassword(pass);
		return this;
	}
	
	public UserEntityBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public UserEntityBuilder withLastname(String lastname) {
		this.entity.setLastname(lastname);
		return this;
	}

	public static UserEntityBuilder createUser(Long id) {
		return new UserEntityBuilder(id);
	}

	public static UserEntityBuilder createUser() {
		return new UserEntityBuilder();
	}
}
