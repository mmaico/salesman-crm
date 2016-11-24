package br.com.kproj.salesman.accounts.customers.domain.model.address;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class AddressBuilder extends AbstractBuilder<Address>  {

	public AddressBuilder() {
		this.entity = new Address();
	}

	public AddressBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static AddressBuilder createAddress(Long id) {
		return new AddressBuilder(id);
	}

}
