package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tasks")
public class Task extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @OneToMany
    @JoinColumn(name="parent_id")
    private List<Task> tasks;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Checklist> checklist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<TaskCost> taskCosts;

    @ManyToOne
    @JoinColumn(name="sales_order_id")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User signedBy;

    @Override
    public Long getId() {
        return this.id;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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

    public List<Checklist> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<Checklist> checklist) {
        this.checklist = checklist;
    }

    public List<TaskCost> getTaskCosts() {
        return taskCosts;
    }

    public void setTaskCosts(List<TaskCost> taskCosts) {
        this.taskCosts = taskCosts;
    }

    public User getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(User signedBy) {
        this.signedBy = signedBy;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
