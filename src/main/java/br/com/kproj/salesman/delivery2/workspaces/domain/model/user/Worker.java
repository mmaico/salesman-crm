package br.com.kproj.salesman.delivery2.workspaces.domain.model.user;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Worker extends ModelIdentifiable {

    private Long id;

    public Worker(){}
    public Worker(Long id) {
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
