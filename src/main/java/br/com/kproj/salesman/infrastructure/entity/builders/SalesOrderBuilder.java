package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderItem;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderPaymentItem;

import java.util.Date;
import java.util.List;

public class SalesOrderBuilder extends AbstractBuilder<SalesOrderEntity>  {

	public SalesOrderBuilder() {
		this.entity = new SalesOrderEntity();
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

    public SalesOrderBuilder withSeller(UserEntity user) {
        this.entity.setSeller(user);
        return this;
    }

    public SalesOrderBuilder withCurrentDate() {
        this.entity.setCreationDate(new Date());
        return this;
    }

    public SalesOrderBuilder withDeliveryForeCast(Date deliveryForeCast) {
        this.entity.setDeliveryForecast(deliveryForeCast);
        return this;
    }

    public SalesOrderBuilder withOperationRegion(OperationRegionEntity operationRegionEntity) {
        this.entity.setOperationRegionEntity(operationRegionEntity);
        return this;
    }

    public SalesOrderBuilder withSalesOrderItems(List<SalesOrderItem> items) {
        this.entity.setSalesOrderItems(items);
        return this;
    }

    public SalesOrderBuilder withProposal(BusinessProposalEntity proposal) {
        this.entity.setProposal(proposal);
        return this;
    }

    public SalesOrderBuilder addSalesOrderItem(SalesOrderItem item) {
        item.setSalesOrderEntity(this.entity);
        this.entity.addSalesOrderItem(item);
        return this;
    }

    public SalesOrderBuilder addPayment(SalesOrderPaymentItem item) {
        item.setSalesOrderEntity(this.entity);
        this.entity.addPayment(item);
        return this;
    }



}
