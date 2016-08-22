package br.com.kproj.salesman.infrastructure.entity.accounts;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="accounts")
public class AccountEntity extends Identifiable {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String site;
    private String description;
    private String phone;
    private Integer employers;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    private List<AddressEntity> addresses;

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

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }
}
