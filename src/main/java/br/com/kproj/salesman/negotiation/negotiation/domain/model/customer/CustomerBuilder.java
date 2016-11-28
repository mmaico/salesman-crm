package br.com.kproj.salesman.negotiation.negotiation.domain.model.customer;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class CustomerBuilder extends AbstractBuilder<Customer>  {

	public CustomerBuilder() {
		this.entity = new Customer();
	}

	public CustomerBuilder(Long id) {
		this();
		this.entity.setId(id);
	}


	public static CustomerBuilder createAccount(Long id) {
		return new CustomerBuilder(id);
	}

	public static CustomerBuilder createAccount() {
		return new CustomerBuilder();
	}
}
