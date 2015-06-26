package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Vendor extends AbstractEntity implements Accessor {


    @NotNull
    private final String name;
    @OneToOne
    private final User user;
    @OneToMany
    private Collection<Client> clients;
    @OneToMany
    private Collection<Project> saleableProjects;

    public Vendor() {
        setId(null);
        name = null;
        user = null;
        initCollections();
    }

    public Vendor(Long id) {
        setId(id);
        initCollections();
        name = null;
        user = null;
    }

    public Vendor(String name, User user) {
        setId(null);
        initCollections();
        this.name = name;
        this.user = user;
    }

    private void initCollections() {
        clients = new HashSet<Client>(3);
        saleableProjects = new HashSet<Project>(3);
    }

    public Iterable<Client> getClients() {
        return clients;
    }

    public Iterable<Project> getSaleableProjects() {
        return saleableProjects;
    }

    public void addClient(Client client) {
        if (clients == null) {
            throw new IllegalStateException("clients collection was not created");
        }
        clients.add(client);
    }

    public void addSaleableProject(Project project) {
        if (project == null) {
            throw new IllegalStateException("projects collection was not created");
        }
        saleableProjects.add(project);
    }

    public String getName() {
        return name;
    }

    @Override
    public User getUser() {
        return user;
    }
}
