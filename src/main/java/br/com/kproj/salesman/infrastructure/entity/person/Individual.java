package br.com.kproj.salesman.infrastructure.entity.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;


@Entity
@DiscriminatorValue("individual")
public class Individual extends Person {

	private static final long serialVersionUID = -1209371318871861717L;
	
	@Size(max = 20, message = "company.invalid.cpf")
	private String cpf;

    private String lastname;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
