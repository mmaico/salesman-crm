package br.com.kproj.salesman.assistants.activities.domain.model.checklist;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class AddChecklistInActivity implements ValueObject {

    private final Checklist checklist;
    private final Activity activity;

    public AddChecklistInActivity(Checklist checklist, Activity activity) {
        this.checklist = checklist;
        this.activity = activity;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public Activity getActivity() {
        return activity;
    }

}
