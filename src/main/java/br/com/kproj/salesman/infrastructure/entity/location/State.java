package br.com.kproj.salesman.infrastructure.entity.location;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State extends Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 699235843576341807L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
