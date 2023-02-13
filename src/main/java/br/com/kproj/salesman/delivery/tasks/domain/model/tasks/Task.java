package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;

import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.Checklist;
import br.com.kproj.salesman.delivery.tasks.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.Subscriber;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Model
public class Task extends ModelIdentifiable {

    private Long id;

    private String title;
    private String description;

    @NotNull(message = "task.deadline.not.informed")
    private Date deadline;

    private TaskStatus status;
    private List<Checklist> checklists;
    private Delivery delivery;
    private List<Subscriber> subscribers;
    private Represent represent;

    @Autowired
    private ChecklistRepository checklistRepository;

    public Task() {
        AutowireHelper.autowire(this);
    }

    public Task(Long id) {
        this();
        this.id = id;
    }

//    public void addCheckList(Checklist checklist) {
//        checklistRepository.register(checklist, this);
//    }


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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(List<Checklist> checklists) {
        this.checklists = checklists;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void addChecklist(Checklist checklist) {
         if (this.checklists == null) {
            this.checklists = Lists.newArrayList();
         }
         this.checklists.add(checklist);
    }

    public void addSubscriber(Subscriber subscriber) {
        if (this.subscribers == null) {
            this.subscribers = Lists.newArrayList();
        }
        this.subscribers.add(subscriber);
    }

    public Represent getRepresent() {
        return represent;
    }

    public void setRepresent(Represent represent) {
        this.represent = represent;
    }

    public static Task task() {
        return new Task();
    }
}
