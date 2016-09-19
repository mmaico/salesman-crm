package br.com.kproj.salesman.assistants.activities2.domain.model.checklist;

import br.com.kproj.salesman.assistants.activities2.domain.model.personal.Activity;


public class AddChecklistInActivity {

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
