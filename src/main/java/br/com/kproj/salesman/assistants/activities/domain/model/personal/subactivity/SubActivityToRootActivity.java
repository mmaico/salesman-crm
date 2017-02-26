package br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class SubActivityToRootActivity implements ValueObject {

    private final SubActivity subActivity;
    private final Activity parent;


    public SubActivityToRootActivity(SubActivity subActivity, Activity parent) {
        this.subActivity = subActivity;
        this.parent = parent;
    }

    public SubActivity getSubActivity() {
        return subActivity;
    }

    public Activity getParent() {
        return parent;
    }

    public static SubActivityToRootActivity newSubActivity(SubActivity subActivity, Activity parent) {
        return new SubActivityToRootActivity(subActivity, parent);
    }
}
