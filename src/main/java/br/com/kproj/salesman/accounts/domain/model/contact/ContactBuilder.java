package br.com.kproj.salesman.accounts.domain.model.contact;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class ContactBuilder extends AbstractBuilder<Contact>  {

	public ContactBuilder() {
		this.entity = new Contact();
	}

	public ContactBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ContactBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	public ContactBuilder withEmail(String email) {
		this.entity.setEmail(email);
		return this;
	}

	public ContactBuilder withPhone(String phone) {
		this.entity.setPhone(phone);
		return this;
	}

	public ContactBuilder withPosition(String position) {
		this.entity.setPosition(position);
		return this;
	}

	public static ContactBuilder createContact(Long id) {
		return new ContactBuilder(id);
	}

	public static ContactBuilder createContact() {
		return new ContactBuilder();
	}
}
