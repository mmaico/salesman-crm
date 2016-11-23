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
	
	public AddressBuilder withStreet(String street) {
		this.entity.setStreet(street);
		return this;
	}
	
	public AddressBuilder withCity(String city) {
		this.entity.setCity(city);
		return this;
	}
	
	public AddressBuilder withState(String state) {
		this.entity.setState(state);
		return this;
	}
	
	public AddressBuilder withZipCode(String zipCode) {
		this.entity.setZipCode(zipCode);
		return this;
	}

	public AddressBuilder withCountry(String country) {
		this.entity.setCountry(country);
		return this;
	}

	public AddressBuilder withType(Address.Type type) {
		this.entity.setType(type);
		return this;
	}
	
	public static AddressBuilder createAddress(Long id) {
		return new AddressBuilder(id);
	}

	public static AddressBuilder createAddress() {
		return new AddressBuilder();
	}
}
