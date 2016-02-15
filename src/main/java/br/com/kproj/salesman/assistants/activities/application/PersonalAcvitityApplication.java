package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface PersonalAcvitityApplication extends ModelService<PersonalActivity> {

    PersonalActivity register(PersonalActivity personalActivity);
}
