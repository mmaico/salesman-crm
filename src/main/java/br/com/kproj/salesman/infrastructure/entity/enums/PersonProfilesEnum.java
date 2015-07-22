package br.com.kproj.salesman.infrastructure.entity.enums;

import static java.util.Arrays.asList;

import java.util.stream.Collectors;

import br.com.kproj.salesman.infrastructure.entity.person.Profile;

public enum PersonProfilesEnum {

	COMPANY_CLIENT(new Profile(1l)),
	INDIVIDUAL_CLIENT(new Profile(2l)),
	COMPANY_PROVIDER(new Profile(3l)),
	INDIVIDUAL_PROVIDER(new Profile(4l));
	
	private Profile profile;
	
	private PersonProfilesEnum(Profile profile) {
		this.profile = profile;
	}
	
	public Profile get(){
		return profile;
	}
	
	public Iterable<Profile> getAll() {	
		return asList(values()).stream()
					.map(PersonProfilesEnum::get)
					.collect(Collectors.toList());
		
	}
}
