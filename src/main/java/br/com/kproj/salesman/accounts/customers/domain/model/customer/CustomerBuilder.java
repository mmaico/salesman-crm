package br.com.kproj.salesman.accounts.customers.domain.model.customer;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class CustomerBuilder extends AbstractBuilder<Customer>  {

	public CustomerBuilder() {
		this.entity = new Customer();
	}

	public CustomerBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public CustomerBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public CustomerBuilder withSite(String site) {
		this.entity.setSite(site);
		return this;
	}
	
	public CustomerBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}

	
	public static CustomerBuilder createAccount(Long id) {
		return new CustomerBuilder(id);
	}

	public static CustomerBuilder createAccount() {
		return new CustomerBuilder();
	}
}
