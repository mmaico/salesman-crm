package br.com.kproj.salesman.infrastructure.entity.delivery;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="deliveries")
public class DeliveryEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "salesorder_id")
    private SalesOrderEntity salesOrder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="delivery_forecast")
    private Date deliveryForecast;

    private List<WorkerEntity> workers;


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

    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }
}
