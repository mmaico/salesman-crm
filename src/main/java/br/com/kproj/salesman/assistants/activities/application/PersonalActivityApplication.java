package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface PersonalActivityApplication extends ModelService<PersonalActivity> {

    PersonalActivity register(PersonalActivity personalActivity);

    PersonalActivity registerSubtask(PersonalActivity parent, PersonalActivity taskChild);


    void changeStatus(PersonalActivity activity, User userChanger);

}