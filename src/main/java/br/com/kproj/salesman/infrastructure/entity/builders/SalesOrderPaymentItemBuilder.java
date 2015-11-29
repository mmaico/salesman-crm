package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderPaymentItem;

import java.math.BigDecimal;
import java.util.Date;

public class SalesOrderPaymentItemBuilder extends AbstractBuilder<SalesOrderPaymentItem>  {

	public SalesOrderPaymentItemBuilder() {
		this.entity = new SalesOrderPaymentItem();
	}

	public SalesOrderPaymentItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public static SalesOrderPaymentItemBuilder createSalesOrderPaymentItem(Long id) {
		return new SalesOrderPaymentItemBuilder(id);
	}

	public static SalesOrderPaymentItemBuilder createSalesOrderPaymentItem() {
		return new SalesOrderPaymentItemBuilder();
	}

    public SalesOrderPaymentItemBuilder withDueDate(Date duedate) {
        this.entity.setDueDate(duedate);
        return this;
    }

    public SalesOrderPaymentItemBuilder withValue(BigDecimal value) {
        this.entity.setValue(value);
        return this;
    }

    public SalesOrderPaymentItemBuilder withObservation(String observation) {
        this.entity.setObservation(observation);
        return this;
    }

    public SalesOrderPaymentItemBuilder withSalesOrder(SalesOrder salesOrder) {
        this.entity.setSalesOrder(salesOrder);
        return this;
    }

}
