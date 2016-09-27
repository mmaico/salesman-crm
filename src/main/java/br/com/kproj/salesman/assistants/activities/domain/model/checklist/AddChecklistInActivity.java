package br.com.kproj.salesman.assistants.activities.domain.model.checklist;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.model.ValueObject;


public class AddChecklistInActivity implements ValueObject {

    private final Checklist checklist;
    private final Activity activity;
    private final Owner owner;

    public AddChecklistInActivity(Checklist checklist, Activity activity, Owner owner) {
        this.checklist = checklist;
        this.activity = activity;
        this.owner = owner;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public Activity getActivity() {
        return activity;
    }

    public Owner getOwner() {
        return owner;
    }
}
