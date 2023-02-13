package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.Subtask;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.subtasks.SubtaskRepository;
import com.github.mmaico.shared.annotations.Model;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Model
public class RootTask extends Task {

    private List<Subtask> children = Lists.newArrayList();


    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private RootTaskRepository repository;

    public RootTask() {
        AutowireHelper.autowire(this);
    }
    public RootTask(Long id) {
        this();
        this.setId(id);
    }

    public Optional<RootTask> makeSpecialization() {
        return repository.save(this);
    }

    public List<Subtask> getChildren() {
        return children;
    }

    public void setChildren(List<Subtask> children) {
        this.children = children;
    }

    public Optional<Subtask> addSubTask(Subtask subtask) {
        return subtaskRepository.add(subtask, this);
    }

}
