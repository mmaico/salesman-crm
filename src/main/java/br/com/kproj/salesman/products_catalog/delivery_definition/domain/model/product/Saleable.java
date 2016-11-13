package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product;

import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks.RootTaskRepository;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class Saleable extends ModelIdentifiable {

    private Long id;

    @Autowired
    private RootTaskRepository taskRepository;

    public Saleable(Long id) {
        this();
        this.id = id;
    }

    public Saleable() {
        autowire(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<RootTask> addRootTask(RootTask task) {
        return taskRepository.add(task, this);
    }
}
