package br.com.kproj.salesman.administration.approval_negotiation.domain.model.approver;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Approver extends ModelIdentifiable {

    private Long id;

    private Boolean available;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean isAvailable() {
        return this.available;
    }
}
