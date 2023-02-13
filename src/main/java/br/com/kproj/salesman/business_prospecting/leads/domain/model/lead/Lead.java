package br.com.kproj.salesman.business_prospecting.leads.domain.model.lead;


import br.com.kproj.salesman.business_prospecting.leads.domain.model.address.Addresses;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.phone.Phones;
import br.com.kproj.salesman.business_prospecting.leads.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Lead extends ModelIdentifiable {

    private Long id;

    private String name;
    private String email;
    private String position;
    private String company;
    private Phones phones;
    private Addresses addresses;
    private User createdBy;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Phones getPhones() {
        return phones;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
