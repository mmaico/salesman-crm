package br.com.kproj.salesman.infrastructure.entity.enums;

import static java.util.Arrays.asList;

import java.util.stream.Collectors;

import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;

public enum PersonProfilesEnum {

	COMPANY_CLIENT(new PersonProfile(1l, "Cliente Pessoa Juridica")),
	INDIVIDUAL_CLIENT(new PersonProfile(2l, "Cliente Pessoa Fisica")),
	COMPANY_PROVIDER(new PersonProfile(3l, "Fornecedor Pessoa Jurifica")),
	INDIVIDUAL_PROVIDER(new PersonProfile(4l, "Fornecedor Pessoa fisica"));
	
	private PersonProfile profile;
	
	private PersonProfilesEnum(PersonProfile profile) {
		this.profile = profile;
	}
	
	public PersonProfile get(){
		return profile;
	}
	
	public static Iterable<PersonProfile> getAll() {	
		return asList(values()).stream()
					.map(PersonProfilesEnum::get)
					.collect(Collectors.toList());
		
	}
}
