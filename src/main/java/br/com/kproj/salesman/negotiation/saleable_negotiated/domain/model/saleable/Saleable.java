package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Saleable saleable = (Saleable) o;

        return id != null ? id.equals(saleable.id) : saleable.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
