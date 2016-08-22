package br.com.kproj.salesman.administration.users.domain.model.branch;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class BranchBuilder extends AbstractBuilder<Branch>  {

	public BranchBuilder() {
		this.entity = new Branch();
	}

	public BranchBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public BranchBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	
	public static BranchBuilder createBranch(Long id) {
		return new BranchBuilder(id);
	}

	public static BranchBuilder createBranch() {
		return new BranchBuilder();
	}
}
