package br.com.kproj.salesman.infrastructure.entity.person;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "profile")
public class Profile extends Identifiable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getClientCompany() {

        return null;
    }

    public Profile getClientIndividual() {
        return null;
    }

    public Profile getProviderCompany() {
        return null;
    }

    public Profile getProviderIndividual() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile {");
        sb.append("id=").append(getId());
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
