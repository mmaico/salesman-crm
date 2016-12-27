package br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity;


import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;

public class SubActivity extends Activity {

    private Activity parent;

    public SubActivity() {
    }
    public SubActivity(Long id) {
        super.setId(id);
    }



    public Activity getParent() {
        return parent;
    }

    public void setParent(Activity parent) {
        this.parent = parent;
    }
}
