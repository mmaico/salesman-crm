package br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Compaing;
import com.trex.shared.annotations.Model;

@Model
public class ActivityCampaign extends Activity {

    private Compaing compaing;

    public Compaing getCompaing() {
        return compaing;
    }

    public void setCompaing(Compaing compaing) {
        this.compaing = compaing;
    }
}
