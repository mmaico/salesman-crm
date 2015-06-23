package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Project extends Product {

    @NotNull
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    public Project() {
        name = null;
    }

    public String getName() {
        return name;
    }
}
