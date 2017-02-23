package br.com.kproj.salesman.medias.storage.domain.model.definition;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;


public class StorageBuilder extends AbstractBuilder<Storage>  {

	public StorageBuilder() {
		this.entity = new Storage();
	}

	public StorageBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public StorageBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	public static StorageBuilder createStorage(Long id) {
		return new StorageBuilder(id);
	}

	public static StorageBuilder createStorage() {
		return new StorageBuilder();
	}
}
