package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Saleable;
import com.trex.shared.annotations.Model;

@Model
public class ActivitySaleable extends Activity {

    private Saleable saleable;

    public Saleable getSaleable() {
        return saleable;
    }

    public void setSaleable(Saleable saleable) {
        this.saleable = saleable;
    }
}
