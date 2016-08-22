package br.com.kproj.salesman.accounts.domain.model.account;

import br.com.kproj.salesman.accounts.domain.model.address.Address;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.google.common.collect.Lists;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class Account extends ModelIdentifiable {

    private Long id;

    @NotNull(message = "account.name.is.invalid")
    @Size(min = 2, max = 150, message = "account.name.is.invalid")
    private String name;
    private String site;
    private String description;
    private String phone;
    private Integer employers = 0;
    private List<Address> addresses = Lists.newArrayList();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getEmployers() {
        return employers;
    }

    public void setEmployers(Integer employers) {
        this.employers = employers;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
