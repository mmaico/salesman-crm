package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderPaymentItem;

import java.util.Date;
import java.util.List;

public class SalesOrderBuilder extends AbstractBuilder<SalesOrder>  {

	public SalesOrderBuilder() {
		this.entity = new SalesOrder();
	}

	public SalesOrderBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalesOrderBuilder createSalesOrder(Long id) {
		return new SalesOrderBuilder(id);
	}

	public static SalesOrderBuilder createSalesOrder() {
		return new SalesOrderBuilder();
	}

    public SalesOrderBuilder withClient(Person client) {
        this.entity.setClient(client);
        return this;
    }

    public SalesOrderBuilder withSeller(User user) {
        this.entity.setSeller(user);
        return this;
    }

    public SalesOrderBuilder withDeliveryForeCast(Date deliveryForeCast) {
        this.entity.setDeliveryForecast(deliveryForeCast);
        return this;
    }

    public SalesOrderBuilder withOperationRegion(OperationRegion operationRegion) {
        this.entity.setOperationRegion(operationRegion);
        return this;
    }

    public SalesOrderBuilder withSalesOrderItems(List<SalesOrderItem> items) {
        this.entity.setSalesOrderItems(items);
        return this;
    }

    public SalesOrderBuilder addSalesOrderItem(SalesOrderItem item) {
        item.setSalesOrder(this.entity);
        this.entity.addSalesOrderItem(item);
        return this;
    }

    public SalesOrderBuilder addPayment(SalesOrderPaymentItem item) {
        item.setSalesOrder(this.entity);
        this.entity.addPayment(item);
        return this;
    }



}
