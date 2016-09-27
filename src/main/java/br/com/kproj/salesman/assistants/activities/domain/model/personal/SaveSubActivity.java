package br.com.kproj.salesman.assistants.activities.domain.model.personal;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class SaveSubActivity implements ValueObject {

    private final SubActivity subActivity;
    private final Activity parent;
    private final Owner owner;


    public SaveSubActivity(SubActivity subActivity, Activity parent, Owner owner) {
        this.subActivity = subActivity;
        this.owner = owner;
        this.parent = parent;
    }

    public SubActivity getSubActivity() {
        return subActivity;
    }

    public Owner getOwner() {
        return owner;
    }

    public Activity getParent() {
        return parent;
    }

    public static SaveSubActivity newChange(SubActivity subActivity, Activity parent, Owner user) {
        return new SaveSubActivity(subActivity, parent, user);
    }
}
