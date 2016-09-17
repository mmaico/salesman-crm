package br.com.kproj.salesman.delivery.workspaces.domain.model.workspace;


import br.com.kproj.salesman.delivery.workspaces.domain.model.user.Worker;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface WorkspaceRepository extends BaseRepository<Workspace, Long> {


    void addWorkerIn(Workspace workspace, Worker worker);

    void removeWorkerFrom(Workspace workspace, Worker worker);
}
