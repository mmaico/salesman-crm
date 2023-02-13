package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.TaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.SubtaskRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.delivery.tasks.domain.services.AddChecklistInTask;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Model
public class Subscriber extends ModelIdentifiable {

    private Long id;
    private User user;
    private Task task;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private ChecklistRepository checklistRepository;

    public Subscriber(Long id) {
        this();
        this.id = id;
    }

    public Subscriber() {
        AutowireHelper.autowire(this);
    }

    public Optional<Task> save(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Subtask> save(Subtask subtask) {
        return subtaskRepository.add(subtask, new RootTask(subtask.getParent().getId()));
    }

    public AddChecklistInTask save(Checklist checklist) {
        return task -> checklistRepository.register(checklist, task);
    }

    public Checklist update(Checklist checklist) {
        return checklistRepository.update(checklist);
    }

    public static Subscriber subscriber() {
        return new Subscriber();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
