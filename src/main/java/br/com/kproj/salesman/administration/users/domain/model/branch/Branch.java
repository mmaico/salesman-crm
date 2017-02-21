package br.com.kproj.salesman.administration.users.domain.model.branch;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;


public class Branch extends ModelIdentifiable {

    private Long id;

    private String name;

    public Branch(){}

    public Branch(Long id){
        this.id = id;
    }

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
