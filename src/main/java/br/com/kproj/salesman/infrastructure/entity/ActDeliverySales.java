package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;
import br.com.kproj.salesman.infrastructure.entity.location.City;
import br.com.kproj.salesman.infrastructure.entity.location.Country;
import br.com.kproj.salesman.infrastructure.entity.location.State;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="act_delivery_sales")
public class ActDeliverySales extends Identifiable {

	private static final long serialVersionUID = 2011235325379126884L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sales_order_id")
    @NotNull(message = "act.delivery.sales.order.is.null")
	private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @NotNull(message = "act.delivery.user.is.null")
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
