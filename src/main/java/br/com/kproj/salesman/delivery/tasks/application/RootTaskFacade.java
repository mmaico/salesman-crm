package br.com.kproj.salesman.delivery.tasks.application;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface RootTaskFacade extends ModelFacade<RootTask> {

    Optional<RootTask> register(RootTask task);


}
