package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;

public class ContactBuilder extends AbstractBuilder<ContactEntity>  {

	public ContactBuilder() {
		this.entity = new ContactEntity();
	}

	public ContactBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static ContactBuilder createContact(Long id) {
		return new ContactBuilder(id);
	}

	public static ContactBuilder createContact() {
		return new ContactBuilder();
	}
}
