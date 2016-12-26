package br.com.kproj.salesman.assistants.calendar.activities.specialization.application;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivitySaleable;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface ActivitySaleableFacade extends ModelFacade<ActivitySaleable> {

    Optional<ActivitySaleable> makeSpecialization(ActivitySaleable activitySaleable);

}
