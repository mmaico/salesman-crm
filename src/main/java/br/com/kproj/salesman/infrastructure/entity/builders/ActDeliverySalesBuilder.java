package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;

public class ActDeliverySalesBuilder extends AbstractBuilder<WorkspaceUnit>  {

	public ActDeliverySalesBuilder() {
		this.entity = new WorkspaceUnit();
	}

	public ActDeliverySalesBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ActDeliverySalesBuilder withSalesOrder(SalesOrderEntity salesOrderEntity) {
		this.entity.setSalesOrderEntity(salesOrderEntity);
		return this;
	}

	public ActDeliverySalesBuilder withUser(UserEntity user) {
		this.entity.setUser(user);
		return this;
	}

	public static ActDeliverySalesBuilder createActDelivery(Long id) {
		return new ActDeliverySalesBuilder(id);
	}

	public static ActDeliverySalesBuilder createActDelivery() {
		return new ActDeliverySalesBuilder();
	}
}
