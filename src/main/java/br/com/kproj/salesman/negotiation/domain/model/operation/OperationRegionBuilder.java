package br.com.kproj.salesman.negotiation.domain.model.operation;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class OperationRegionBuilder extends AbstractBuilder<OperationRegion>  {

	public OperationRegionBuilder() {
		this.entity = new OperationRegion();
	}

	public OperationRegionBuilder(Long id) {
		this();
		this.entity.setId(id);
	}



	public static OperationRegionBuilder createRegion(Long id) {
		return new OperationRegionBuilder(id);
	}

	public static OperationRegionBuilder createRegion() {
		return new OperationRegionBuilder();
	}
}
