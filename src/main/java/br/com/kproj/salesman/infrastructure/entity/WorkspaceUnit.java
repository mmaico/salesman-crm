package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="workspace_units")
public class WorkspaceUnit extends Identifiable {

	private static final long serialVersionUID = 2011235325379126884L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sales_order_id")
    @NotNull(message = "act.delivery.sales.order.is.null")
	private SalesOrderEntity salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @NotNull(message = "act.delivery.user.is.null")
    private UserEntity user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrderEntity getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrderEntity salesOrder) {
        this.salesOrder = salesOrder;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
