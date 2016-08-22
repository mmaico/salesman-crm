package br.com.kproj.salesman.administration.users.domain.model.user;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class PositionBuilder extends AbstractBuilder<Position>  {

	public PositionBuilder() {
		this.entity = new Position();
	}

	public PositionBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public PositionBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	
	public static PositionBuilder createPosition(Long id) {
		return new PositionBuilder(id);
	}

	public static PositionBuilder createPosition() {
		return new PositionBuilder();
	}
}
