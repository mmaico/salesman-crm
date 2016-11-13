package br.com.kproj.salesman.products_catalog.delivery_definition.view.support.resources;


import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "quantityDaysToFinish",
        "links"
})
@ResourceItem(name="task-definitions", modelReference = Task.class, parent = SaleableResource.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskResource extends Item {

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

    @Selectable(expression = "of-region", externalLink = true)
    public RegionResource getRegion() {
        return region;
    }

    public void setRegion(RegionResource region) {
        this.region = region;
    }

    @Selectable(expression = "of-saleable", externalLink = true)
    public SaleableResource getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableResource saleable) {
        this.saleable = saleable;
    }

    public Long getRegionId() {
        return region == null ? null : region.getId();
    }

}
