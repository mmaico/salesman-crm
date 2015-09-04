package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;

//@Entity
//@Table(name="product")
public class Service extends SaleableUnit {


	private static final long serialVersionUID = -5564194680450611962L;
	
	public Service(){}
    public Service(String name) {
        super(name);
    }


    @Override
    public Long getId() {
        return null;
    }
}
