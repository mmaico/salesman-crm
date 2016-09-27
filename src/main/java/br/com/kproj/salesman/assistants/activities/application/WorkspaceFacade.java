package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.*;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;

import java.util.Optional;

public interface WorkspaceFacade {

    Optional<Activity> register(SaveActivity activity);

    Optional<SubActivity> register(SaveSubActivity saveSubActivity);

    void changeActivityStatus(Owner owner, ChangeStatus changeStatus);

}
