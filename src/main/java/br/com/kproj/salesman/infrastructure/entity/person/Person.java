package br.com.kproj.salesman.infrastructure.entity.person;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "persons")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class Person extends Identifiable {

    @NotNull
    @Size(min = 2, max = 30, message = "name")
    private String name;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "person")
    protected List<Contact> contacts;

    @NotNull
    private Boolean active = Boolean.TRUE;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="profile_id")
    @NotNull
    private Profile profile;

    public Person() {
        super();
    }

    public Person(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append(", profile='").append(profile).append('\'');
        sb.append('}');
        return sb.toString();
    }

}