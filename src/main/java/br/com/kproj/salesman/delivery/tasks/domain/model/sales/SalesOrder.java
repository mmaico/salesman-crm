package br.com.kproj.salesman.delivery.tasks.domain.model.sales;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;
import com.google.common.collect.Lists;


import java.util.List;

@Model
public class SalesOrder extends ModelIdentifiable {

    private Long id;
    private Region region;

    private List<SalesItem> items = Lists.newArrayList();

    public SalesOrder(Long id) {
        this.id = id;
    }

    public SalesOrder() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SalesItem> getItems() {
        return items;
    }

    public void setItems(List<SalesItem> items) {
        this.items = items;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
