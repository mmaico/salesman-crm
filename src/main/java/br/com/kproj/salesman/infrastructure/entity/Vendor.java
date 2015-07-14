package br.com.kproj.salesman.infrastructure.entity;

import com.google.common.collect.Lists;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class Vendor extends Identifiable implements Accessor {

    @NotNull
    private  String name;
    @OneToOne
    private  User user;
    @OneToMany
    private Collection<Client> clients = Lists.newArrayList();
    @OneToMany
    private Collection<Project> saleableProjects = Lists.newArrayList();


    public Iterable<Client> getClients() {
        return clients;
    }

    public Iterable<Project> getSaleableProjects() {
        return saleableProjects;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addSaleableProject(Project project) {
        saleableProjects.add(project);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    public void setSaleableProjects(Collection<Project> saleableProjects) {
        this.saleableProjects = saleableProjects;
    }

    public String getName() {
        return name;
    }

    @Override
    public User getUser() {
        return user;
    }
}
