package br.com.kproj.salesman.administration.approval_negotiation.domain.model.requester;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Requester extends ModelIdentifiable {

    private Long id;

    public Requester(){}
    public Requester(Long id){
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
