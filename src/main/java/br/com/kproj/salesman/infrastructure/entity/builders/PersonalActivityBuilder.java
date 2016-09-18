package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;

public class PersonalActivityBuilder extends AbstractBuilder<PersonalActivityEntity>  {

	public PersonalActivityBuilder() {
		this.entity = new PersonalActivityEntity();
	}

	public PersonalActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public PersonalActivityBuilder withStatus(PersonalAcvitityStatus status) {
        this.entity.setStatus(status);
        return this;
    }

	public static PersonalActivityBuilder createActivity(Long id) {
		return new PersonalActivityBuilder(id);
	}

	public static PersonalActivityBuilder createActivity() {
		return new PersonalActivityBuilder();
	}
}
