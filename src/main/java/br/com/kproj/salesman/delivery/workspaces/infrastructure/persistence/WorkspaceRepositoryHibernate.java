package br.com.kproj.salesman.delivery.workspaces.infrastructure.persistence;

import br.com.kproj.salesman.delivery.workspaces.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.Workspace;
import br.com.kproj.salesman.delivery.workspaces.domain.model.workspace.WorkspaceRepository;
import br.com.kproj.salesman.delivery.workspaces.infrastructure.persistence.springdata.WorkspaceUnitRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("workspaceRepositoryHibernateWorkspaceModule")
public class WorkspaceRepositoryHibernate extends BaseRespositoryImpl<Workspace, WorkspaceUnit> implements WorkspaceRepository {


    private WorkspaceUnitRepositorySpringdata repository;

    @Autowired
    public WorkspaceRepositoryHibernate(WorkspaceUnitRepositorySpringdata repository) {
        this.repository = repository;
    }

    @Override
    public void addWorkerIn(Workspace workspace, Worker worker) {
        WorkspaceUnit workspaceUnit = repository.findOne(workspace.getId());

    }

    @Override
    public void removeWorkerFrom(Workspace workspace, Worker worker) {

    }


    @Override
    public BaseRepositoryLegacy<WorkspaceUnit, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<WorkspaceUnit, Workspace> getConverter() {
        return (taskTemplateEntity, args) -> {

            //terminar o converter

            return null;
        };
    }


}
