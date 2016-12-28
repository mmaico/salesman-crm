package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

public interface ActivityFacade extends ModelFacade<Activity> {

    Activity update(Activity activity);

    Iterable<Activity> findAll(Owner owner, Pageable pageable);


}
