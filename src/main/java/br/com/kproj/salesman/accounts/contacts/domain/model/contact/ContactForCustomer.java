package br.com.kproj.salesman.accounts.contacts.domain.model.contact;


import br.com.kproj.salesman.accounts.contacts.domain.model.customer.Customer;

public class ContactForCustomer {

    private final Contact contact;
    private final Long customerId;

    public ContactForCustomer(Contact contact, Long customerId) {
        this.contact = contact;
        this.customerId = customerId;
    }

    public Contact getContact() {
        return contact;
    }

    public Customer getCustomer() {
        return new Customer(customerId);
    }

    public static ContactForCustomer createContactToCustomer(Long customerId, Contact contact) {
        return new ContactForCustomer(contact, customerId);
    }
}
