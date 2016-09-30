package br.com.kproj.salesman.delivery.workspaces.application.impl;


import br.com.kproj.salesman.delivery.workspaces.application.WorkspaceFacade;
import br.com.kproj.salesman.delivery.workspaces.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.WorkerIn;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.WorkerOut;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.Workspace;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.WorkspaceRepository;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("workspaceFacadeDeliveryModule")
public class WorkspaceServiceImpl extends BaseModelServiceImpl<Workspace> implements WorkspaceFacade {

    @Autowired
    private WorkspaceRepository repository;

    @Override
    public Optional<Workspace> createFor(SalesOrder salesOrder) {
        Workspace workspace = new Workspace(salesOrder);
        Optional<Workspace> workspaceSaved = repository.save(workspace);
        return workspaceSaved;
    }

    @Override
    public void register(WorkerIn workerIn) {
        Optional<Workspace> workspace = repository.findOne(workerIn.getWorkerId());
        workspace.get().addNewWorker(workerIn.getWorker());
    }

    @Override
    public void register(WorkerOut workerOut) {
        Optional<Workspace> workspace = repository.findOne(workerOut.getWorkerId());
        workspace.get().removeWorker(workerOut.getWorker());
    }

    @Override
    public BaseRepository<Workspace, Long> getRepository() {
        return repository;
    }
}
