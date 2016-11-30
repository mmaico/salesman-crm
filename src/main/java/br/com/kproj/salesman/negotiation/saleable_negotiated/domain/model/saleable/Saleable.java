package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Saleable extends ModelIdentifiable {

    private Long id;

    public Saleable(){}
    public Saleable(Long id){
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
