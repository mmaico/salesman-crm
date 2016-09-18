package br.com.kproj.salesman.assistants.activities2.domain.model.personal;


public class SubActivity extends Activity {

    private Activity parent;

    public Activity getParent() {
        return parent;
    }

    public void setParent(Activity parent) {
        this.parent = parent;
    }
}
