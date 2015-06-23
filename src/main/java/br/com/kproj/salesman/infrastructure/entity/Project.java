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

    @Deprecated
    public static void validate(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project is required");
        }
        if (project.getName() == null || "".equals(project.getName())) {
            throw new IllegalArgumentException("Project name is required");
        }
    }

    public String getName() {
        return name;
    }
}
