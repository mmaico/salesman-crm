package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.campaing.Compaing;
import com.github.mmaico.shared.annotations.Model;


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
