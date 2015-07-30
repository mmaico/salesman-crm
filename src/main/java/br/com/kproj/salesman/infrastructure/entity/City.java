package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;

@Entity
public class City extends Identifiable {

	private static final long serialVersionUID = 7816210670472377067L;
	
	private final String name;

    public City(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
