package br.com.kproj.salesman.assistants.calendar.domain.model.relations;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Contact extends ModelIdentifiable {

    private Long id;

    public Contact(){}
    public Contact(Long id){
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
