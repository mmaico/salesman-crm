package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.RootTask;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "quantityDaysToFinish",
        "region",
        "saleable",
        "links"
})
@ResourceItem(name="task-definitions", modelReference = RootTask.class, parent = SaleableResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootTaskResource extends Item {

    private Long id;
    private String title;
    private String description;
    private Integer quantityDaysToFinish;
    private RegionResource region;
    private SaleableResource saleable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityDaysToFinish() {
        return quantityDaysToFinish;
    }

    public void setQuantityDaysToFinish(Integer quantityDaysToFinish) {
        this.quantityDaysToFinish = quantityDaysToFinish;
    }

    public RegionResource getRegion() {
        return region;
    }

    public void setRegion(RegionResource region) {
        this.region = region;
    }

    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }
}
