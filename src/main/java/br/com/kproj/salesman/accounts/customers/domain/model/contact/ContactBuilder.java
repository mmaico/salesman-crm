package br.com.kproj.salesman.accounts.customers.domain.model.contact;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class ContactBuilder extends AbstractBuilder<Contact>  {

	public ContactBuilder() {
		this.entity = new Contact();
	}

	public ContactBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static ContactBuilder createContact(Long id) {
		return new ContactBuilder(id);
	}

}
