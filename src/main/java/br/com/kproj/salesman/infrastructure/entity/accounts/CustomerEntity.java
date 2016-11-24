package br.com.kproj.salesman.infrastructure.entity.accounts;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customers")
public class CustomerEntity extends Identifiable {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String site;
    private String description;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "customer")
    private List<AddressEntity> addresses;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "customer")
    private List<ContactEntity> contacts;

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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }
}
