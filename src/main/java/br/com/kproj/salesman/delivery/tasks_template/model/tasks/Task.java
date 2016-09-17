package br.com.kproj.salesman.delivery.tasks_template.model.tasks;

import br.com.kproj.salesman.delivery.tasks_template.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks_template.model.product.Saleable;
import br.com.kproj.salesman.delivery.tasks_template.model.region.Region;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.List;

@Model
public class Task extends ModelIdentifiable {

    private Long id;
    private String title;
    private String description;
    private List<Subtask> children;
    private Integer quantityDaysToFinish;
    private List<Checklist> checklists;
    private Saleable saleable;
    private Region region;

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

    public List<Subtask> getChildren() {
        return children;
    }

    public void setChildren(List<Subtask> children) {
        this.children = children;
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
