package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;

public class PersonProfileBuilder extends AbstractBuilder<PersonProfile>  {

	public PersonProfileBuilder() {
		this.entity = new PersonProfile();
	}

	public PersonProfileBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public PersonProfileBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}
	
	public static PersonProfileBuilder createPersonProfile(Long id) {
		return new PersonProfileBuilder(id);
	}

	public static PersonProfileBuilder create() {
		return new PersonProfileBuilder();
	}
}
