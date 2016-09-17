package br.com.kproj.salesman.delivery.workspaces.application;


import br.com.kproj.salesman.delivery.workspaces.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.WorkerIn;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.WorkerOut;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.Workspace;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Optional;

public interface WorkspaceFacade extends ModelFacade<Workspace> {

    Optional<Workspace> createFor(SalesOrder salesOrder);

    void register(WorkerIn workerIn);
    void register(WorkerOut workerOut);

}
