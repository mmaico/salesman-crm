package br.com.kproj.salesman.administration.users.domain.model.user;


import br.com.kproj.salesman.administration.users.domain.model.branch.Branch;
import br.com.kproj.salesman.administration.users.domain.model.position.Position;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

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
	
	public UserBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public UserBuilder withLastname(String lastname) {
		this.entity.setLastname(lastname);
		return this;
	}

	public UserBuilder withEmail(String email) {
		this.entity.setEmail(email);
		return this;
	}

	public UserBuilder withBranch(Long branchId) {
		if (branchId != null) {
			this.entity.setBranch(new Branch(branchId));
		}
		return this;
	}

	public UserBuilder withPosition(Long positionId) {
		if (positionId != null) {
			this.entity.setPosition(new Position(positionId));
		}
		return this;
	}

	
	public static UserBuilder createUser(Long id) {
		return new UserBuilder(id);
	}

	public static UserBuilder createUser() {
		return new UserBuilder();
	}
}
