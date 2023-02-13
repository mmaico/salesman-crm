package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.lead.Lead;
import com.github.mmaico.shared.annotations.Model;


@Model
public class ActivityLead extends Activity {

    private Lead lead;

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }
}
