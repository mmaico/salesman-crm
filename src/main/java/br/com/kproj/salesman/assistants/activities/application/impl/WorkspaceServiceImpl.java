package br.com.kproj.salesman.assistants.activities.application.impl;

import br.com.kproj.salesman.assistants.activities.application.WorkspaceFacade;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.*;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.MyWorkspace;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.Workspace;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.workspace.Workspace.workspace;

@Service
public class WorkspaceServiceImpl implements WorkspaceFacade {


    private WorkspaceRepository repository;

    private ActivityValidator activityRules;

    private SubActivityValidator subactivityRules;

    private ChangeStatusValidator changeStatusRules;

    @Autowired
    public WorkspaceServiceImpl(WorkspaceRepository repository, ActivityValidator activityRules,
                                SubActivityValidator subactivityRules, ChangeStatusValidator changeStatusRules) {
        this.repository = repository;
        this.activityRules = activityRules;
        this.subactivityRules = subactivityRules;
        this.changeStatusRules = changeStatusRules;
    }

    @Override
    public Optional<Activity> register(SaveActivity activityToSave) {
        Owner owner = activityToSave.getOwner();
        Activity activity = activityToSave.getActivity();
        activityRules.checkRules(activity);

        return owner.save(activity).ofYour(workspace());
    }

    @Override
    public Optional<SubActivity> register(SaveSubActivity saveSubActivity) {
        subactivityRules.checkRules(saveSubActivity);
        Owner owner = saveSubActivity.getOwner();
        Activity parent = saveSubActivity.getParent();
        SubActivity subActivity = saveSubActivity.getSubActivity();

        return owner.save(subActivity).childOf(parent);
    }

    @Override
    public void changeActivityStatus(Owner owner, ChangeStatus changeStatus) {
        changeStatusRules.checkRules(owner, changeStatus);

        owner.changeStatus(changeStatus.getActivity()).toNewStatus(changeStatus.getStatus());
    }

    public Workspace findOne(MyWorkspace myWorkspace) {
        return repository.findOne(myWorkspace);
    }

}
