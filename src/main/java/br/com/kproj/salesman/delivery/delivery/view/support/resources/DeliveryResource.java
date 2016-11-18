package br.com.kproj.salesman.delivery.delivery.view.support.resources;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;
import java.util.Date;

@JsonPropertyOrder({
        "id",
        "links"
})
@ResourceItem(name="deliveries", modelReference = Delivery.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryResource extends Item {

    private Long id;
    private Date deliveryForecast;
    private SalesOrderResource salesOrder;
    private Collection<WorkerResource> workers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Selectable(expression = "of-salesOrder", externalLink = true)
    public SalesOrderResource getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrderResource salesOrder) {
        this.salesOrder = salesOrder;
    }

    @Selectable(expression = "has-workers", externalLink = true)
    public Collection<WorkerResource> getWorkers() {
        return workers;
    }

    public void setWorkers(Collection<WorkerResource> workers) {
        this.workers = workers;
    }

    public Date getDeliveryForecast() {
        return deliveryForecast;
    }

    public void setDeliveryForecast(Date deliveryForecast) {
        this.deliveryForecast = deliveryForecast;
    }
}
