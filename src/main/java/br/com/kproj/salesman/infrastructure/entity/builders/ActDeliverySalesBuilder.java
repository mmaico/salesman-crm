package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.WorkspaceUnit;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;

public class ActDeliverySalesBuilder extends AbstractBuilder<WorkspaceUnit>  {

	public ActDeliverySalesBuilder() {
		this.entity = new WorkspaceUnit();
	}

	public ActDeliverySalesBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ActDeliverySalesBuilder withSalesOrder(SalesOrder salesOrder) {
		this.entity.setSalesOrder(salesOrder);
		return this;
	}

	public ActDeliverySalesBuilder withUser(User user) {
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
