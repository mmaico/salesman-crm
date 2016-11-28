package br.com.kproj.salesman.negotiation.negotiation.domain.model.operation;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class RegionBuilder extends AbstractBuilder<Region>  {

	public RegionBuilder() {
		this.entity = new Region();
	}

	public RegionBuilder(Long id) {
		this();
		this.entity.setId(id);
	}



	public static RegionBuilder createRegion(Long id) {
		return new RegionBuilder(id);
	}

	public static RegionBuilder createRegion() {
		return new RegionBuilder();
	}
}
