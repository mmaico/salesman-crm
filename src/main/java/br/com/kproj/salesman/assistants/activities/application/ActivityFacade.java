package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.SaveActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActivityFacade extends ModelFacade<Activity> {

    Optional<Activity> register(SaveActivity activity);

    Activity update(Activity activity);

    Iterable<Activity> findAll(Owner owner, Pageable pageable);


}
