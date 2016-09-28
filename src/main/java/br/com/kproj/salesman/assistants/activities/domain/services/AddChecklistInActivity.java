package br.com.kproj.salesman.assistants.activities.domain.services;


import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;

import java.util.Optional;

@FunctionalInterface
public interface AddChecklistInActivity {

    Optional<Checklist> in(Activity activity);
}
