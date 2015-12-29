package br.com.kproj.salesman.infrastructure.entity.person;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.person.privider.Provider;
import com.google.common.collect.Lists;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "persons")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
@Audited
public class Person extends Identifiable implements Client, Provider {

	private static final long serialVersionUID = -6416371282639932944L;

    @Id
    @GeneratedValue
    private Long id;

	@NotNull(message = "person.name.is.invalid")
    @Size(min = 2, max = 150, message = "person.name.is.invalid")
    private String name;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    protected List<Contact> contacts;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
    protected List<Address> addresses;

    @NotNull
    private Boolean active = Boolean.TRUE;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="profile_id")
    @NotNull(message = "person.profile.is.invalid")
    private PersonProfile profile;

    public Person() {
        super();
    }

    public Person(String name) {
        super();
        this.name = name;
    }

    public Person(Long id) {
        super();
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
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

    public PersonProfile getProfile() {
        return profile;
    }

    public void setProfile(PersonProfile profile) {
        this.profile = profile;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addContact(Contact contact) {

        if (this.contacts == null) {
            this.contacts = Lists.newArrayList();
        }

        this.contacts.add(contact);
    }
    
    public void addAddress(Address address) {

        if (this.addresses == null) {
            this.addresses = Lists.newArrayList();
        }

        this.addresses.add(address);
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

    @Override
    public Person to() {
        return this;
    }

}
