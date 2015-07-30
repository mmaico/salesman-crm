package br.com.kproj.salesman.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="product")
public class Service extends Product {


	private static final long serialVersionUID = -5564194680450611962L;
	
	public Service(){}
    public Service(String name) {
        super(name);
    }


}
