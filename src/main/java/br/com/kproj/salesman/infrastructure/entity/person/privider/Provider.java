package br.com.kproj.salesman.infrastructure.entity.person.privider;


import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.PersonProfile;

import java.util.List;

public interface Provider {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    List<Contact> getContacts();

    void setContacts(List<Contact> contacts);

    Boolean getActive();

    void setActive(Boolean active);

    PersonProfile getProfile();

    void setProfile(PersonProfile profile);

    List<Address> getAddresses();

    void setAddresses(List<Address> addresses);

    void addContact(Contact contact);

    void addAddress(Address address);
}
