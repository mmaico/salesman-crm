package br.com.kproj.salesman.assistants.activities.domain.model.personal;


import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.model.ValueObject;

public class SaveActivity implements ValueObject {

    private final Activity activity;
    private final Owner owner;


    public SaveActivity(Activity activity, Owner owner) {
        this.activity = activity;
        this.owner = owner;
    }

    public Activity getActivity() {
        return activity;
    }

    public Owner getOwner() {
        return owner;
    }

    public static SaveActivity newRegister(Activity activity, Owner user) {
        return new SaveActivity(activity, user);
    }
}
