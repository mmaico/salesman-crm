package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist.Checklist;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import com.google.common.collect.Lists;
import com.trex.shared.annotations.Model;

import java.util.List;

@Model
public class Task extends ModelIdentifiable {

    private Long id;
    private String title;
    private String description;
    private Integer quantityDaysToFinish;
    private List<Checklist> checklists = Lists.newArrayList();
    private Saleable saleable;
    private Region region;

    private Represent represent;

    @Override
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

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<Checklist> checklists) {
        this.checklists = checklists;
    }

    public Represent getRepresent() {
        return represent;
    }

    public void setRepresent(Represent represent) {
        this.represent = represent;
    }

    public Saleable getSaleable() {
        return saleable;
    }

    public void setSaleable(Saleable saleable) {
        this.saleable = saleable;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
