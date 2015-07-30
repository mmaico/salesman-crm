package br.com.kproj.salesman.infrastructure.entity.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("individual")
public class Individual extends Person {

	private static final long serialVersionUID = -1209371318871861717L;
	
	private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
