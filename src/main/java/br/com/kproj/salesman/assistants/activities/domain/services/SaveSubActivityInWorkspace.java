package br.com.kproj.salesman.assistants.activities.domain.services;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.SubActivity;

import java.util.Optional;

@FunctionalInterface
public interface SaveSubActivityInWorkspace {

    Optional<SubActivity> childOf(Activity activity);
}
