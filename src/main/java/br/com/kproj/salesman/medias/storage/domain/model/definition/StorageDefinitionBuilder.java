package br.com.kproj.salesman.medias.storage.domain.model.definition;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;


public class StorageDefinitionBuilder extends AbstractBuilder<StorageDefinition>  {

	public StorageDefinitionBuilder() {
		this.entity = new StorageDefinition();
	}

	public StorageDefinitionBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public StorageDefinitionBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	public StorageDefinitionBuilder withEmail(String email) {
		this.entity.setEmail(email);
		return this;
	}

	public StorageDefinitionBuilder withPhone(String phone) {
		this.entity.setPhone(phone);
		return this;
	}

	public StorageDefinitionBuilder withPosition(String position) {
		this.entity.setPosition(position);
		return this;
	}

	public static StorageDefinitionBuilder createContact(Long id) {
		return new StorageDefinitionBuilder(id);
	}

	public static StorageDefinitionBuilder createContact() {
		return new StorageDefinitionBuilder();
	}
}
