package br.com.kproj.salesman.assistants.activities2.domain.model.personal;


public class ChangeStatus {

    private final Activity activity;
    private final Status newStatus;

    public ChangeStatus(Activity activity, Status status) {
        this.activity = activity;
        this.newStatus = status;
    }

    public Activity getActivity() {
        return activity;
    }

    public Status getNewStatus() {
        return newStatus;
    }
}
