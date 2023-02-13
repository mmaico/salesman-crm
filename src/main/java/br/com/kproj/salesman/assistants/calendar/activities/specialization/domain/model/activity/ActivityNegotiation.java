package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.Negotiation;
import com.github.mmaico.shared.annotations.Model;

@Model
public class ActivityNegotiation extends Activity {

    private Negotiation negotiation;

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }
}
