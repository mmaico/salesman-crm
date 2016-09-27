package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activities;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.MyWorkspace;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.Workspace;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class WorkSpaceRepositoryHibernate implements WorkspaceRepository {

    private ActivityRepositoryHibernate repository;

    @Autowired
    public WorkSpaceRepositoryHibernate(ActivityRepositoryHibernate repository) {
        this.repository = repository;
    }


    public Workspace findOne(MyWorkspace myWorkspace) {
        Owner owner = myWorkspace.getOwner();

        Activities activities = repository.findAll(owner);
        return Workspace.workspace(owner.getId(), activities);
    }

}
