package br.com.kproj.salesman.assistants.activities.domain.services;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.Workspace;

import java.util.Optional;

@FunctionalInterface
public interface SaveActivityInWorkspace {

    Optional<Activity> ofYour(Workspace workspace);
}
