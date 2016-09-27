package br.com.kproj.salesman.assistants.activities.domain.model.user;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class OwnerBuilder extends AbstractBuilder<Owner>  {

	public OwnerBuilder() {
		this.entity = new Owner();
	}

	public OwnerBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static OwnerBuilder createOwner(Long id) {
		return new OwnerBuilder(id);
	}

	public static OwnerBuilder createOwner() {
		return new OwnerBuilder();
	}
}
