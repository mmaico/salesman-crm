package br.com.kproj.salesman.assistants.calendar.activities.specialization.application;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface ActivityNegotiationFacade extends ModelFacade<ActivityNegotiation> {

    Optional<ActivityNegotiation> makeSpecialization(ActivityNegotiation activityNegotiation);

}
