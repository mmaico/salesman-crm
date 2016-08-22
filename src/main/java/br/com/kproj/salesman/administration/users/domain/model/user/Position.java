package br.com.kproj.salesman.administration.users.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import javax.validation.constraints.NotNull;


public class Position extends ModelIdentifiable {

    private Long id;

    @NotNull(message = "position.name")
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
