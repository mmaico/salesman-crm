package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;

@Model
public class Negotiation extends ModelIdentifiable {

    private Long id;

    public Negotiation() {}
    public Negotiation(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
