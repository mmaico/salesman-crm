package br.com.kproj.salesman.assistants.activities2.application;


import br.com.kproj.salesman.assistants.activities2.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities2.domain.model.personal.SubActivity;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface ActivityFacade extends ModelFacade<Activity> {

    Optional<SubActivity> register(SubActivity subActivity);
}
