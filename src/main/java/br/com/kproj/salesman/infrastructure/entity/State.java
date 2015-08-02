package br.com.kproj.salesman.infrastructure.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State extends Identifiable {

	private static final long serialVersionUID = -4669203195062744653L;
	
	private String name;

    public State() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
