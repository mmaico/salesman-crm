package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.saleable.Saleable;
import com.github.mmaico.shared.annotations.Model;

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
