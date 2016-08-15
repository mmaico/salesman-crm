package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface PersonalActivityApplication extends ModelLegacyService<PersonalActivity> {

    PersonalActivity register(PersonalActivity personalActivity);

    PersonalActivity registerSubtask(PersonalActivity parent, PersonalActivity taskChild);


    void changeStatus(PersonalActivity activity, User userChanger);

}
