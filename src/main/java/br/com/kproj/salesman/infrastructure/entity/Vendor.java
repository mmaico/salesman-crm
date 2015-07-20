package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
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
    private Collection<Person> clients = Lists.newArrayList();
    @OneToMany
    private Collection<Service> saleableProjects = Lists.newArrayList();


    public Iterable<Person> getClients() {
        return clients;
    }

    public Iterable<Service> getSaleableProjects() {
        return saleableProjects;
    }

    public void addClient(Person client) {
        clients.add(client);
    }

    public void addSaleableProject(Service project) {
        saleableProjects.add(project);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClients(Collection<Person> clients) {
        this.clients = clients;
    }

    public void setSaleableProjects(Collection<Service> saleableProjects) {
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
