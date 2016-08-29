package br.com.kproj.salesman.infrastructure.security.authentication;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

import java.util.Set;



public class LoggedUserBuilder extends AbstractBuilder<LoggedUser> {

	public LoggedUserBuilder() {
		this.entity = new LoggedUser();
	}

	public LoggedUserBuilder(String login, Set<String> roles) {
		this();
		this.entity.setLogin(login);

        roles.forEach(roleUnit -> this.entity.addRole(roleUnit));

	}

	public LoggedUserBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	public LoggedUserBuilder withLogin(String login) {
		this.entity.setLogin(login);
		return this;
	}

	public LoggedUserBuilder withPassword(String password) {
		this.entity.setPassword(password);
		return this;
	}


	public static LoggedUserBuilder createLoggedUser(String login, Set<String> roles) {
		return new LoggedUserBuilder(login, roles);
	}

	public static LoggedUserBuilder createLoggedUser() {
		return new LoggedUserBuilder();
	}
}
