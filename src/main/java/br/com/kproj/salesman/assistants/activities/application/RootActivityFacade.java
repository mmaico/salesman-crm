package br.com.kproj.salesman.assistants.activities.application;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import org.springframework.data.domain.Pageable;

public interface RootActivityFacade extends ModelFacade<RootActivity>  {

    Iterable<RootActivity> findAll(Owner owner, Pageable pageable);

}
