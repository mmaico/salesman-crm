package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Model
public class RootTask extends Task {

    private List<Subtask> children;
    private Saleable saleable;
    private Region region;

    @Autowired
    private SubtaskRepository repository;

    public RootTask() {
        AutowireHelper.autowire(this);
    }
    public RootTask(Long id) {
        this();
        this.setId(id);
    }

    public List<Subtask> getChildren() {
        return children;
    }

    public void setChildren(List<Subtask> children) {
        this.children = children;
    }

    public Optional<Subtask> addSubTask(Subtask subtask) {
        return repository.add(subtask, this);
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
