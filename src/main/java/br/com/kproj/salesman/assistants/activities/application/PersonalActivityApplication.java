package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface PersonalActivityApplication extends ModelLegacyService<PersonalActivityEntity> {

    PersonalActivityEntity register(PersonalActivityEntity personalActivityEntity);

    PersonalActivityEntity registerSubtask(PersonalActivityEntity parent, PersonalActivityEntity taskChild);


    void changeStatus(PersonalActivityEntity activity, UserEntity userChanger);

}
