package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityToRootActivity;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubActivityFacade extends ModelFacade<SubActivity>  {

    Optional<SubActivity> register(SubActivityToRootActivity subActivityToRootActivity);

    Iterable<SubActivity> findAll(RootActivity rootActivity, Pageable pageable);

}
