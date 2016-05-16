package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;
import br.com.kproj.salesman.infrastructure.configuration.annotations.IgnoreField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tasks")
public class Task extends Identifiable implements TimelinePresent {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "task.title.is.null")
    private String title;
    private String description;

    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name="parent_id")
    @ExcludeAuditingField
    private List<Task> tasksChilds;

    @Column(name = "parent_id", updatable =false, insertable = false)
    private Long parentId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "task.deadline.not.informed")
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Checklist> checklist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<TaskCost> taskCosts;

    @ManyToOne
    @JoinColumn(name="sales_order_id")
    @ExcludeAuditingField
    private SalesOrder salesOrder;

    @ManyToMany
    @JoinTable(name="task_user",
            joinColumns={@JoinColumn(name="task_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> signedBy;

    @OneToOne
    @JoinColumn(name = "timeline_id")
    @ExcludeAuditingField
    private Timeline timeline;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<ScheduleTriggerNotification> triggerNotifications;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    private OperationRegion region;

    @Transient
    @IgnoreField
    @ExcludeAuditingField
    private Task parent;

    public void addChild(Task task) {
        if (this.tasksChilds == null) {
            this.tasksChilds = Lists.newArrayList();
        }
        this.tasksChilds.add(task);
    }

    public void addCheckList(Checklist checklist) {
        if (this.checklist == null) {
            this.checklist = Lists.newArrayList();
        }
        this.checklist.add(checklist);
    }

    public void addTaskCost(TaskCost taskCost) {
        if (this.taskCosts == null) {
            this.taskCosts = Lists.newArrayList();
        }
        this.taskCosts.add(taskCost);
    }

    public void addSignedBy(User user) {
        if (this.signedBy == null) {
            this.signedBy = Lists.newArrayList();
        }
        this.signedBy.add(user);
    }

    public void addTriggerNotification(ScheduleTriggerNotification notification) {
        if (this.triggerNotifications == null) {
            this.triggerNotifications = Lists.newArrayList();
        }
        this.triggerNotifications.add(notification);
    }

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

    public List<Task> getTasksChilds() {
        return tasksChilds;
    }

    public void setTasksChilds(List<Task> tasksChilds) {
        this.tasksChilds = tasksChilds;
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

    public List<User> getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(List<User> signedBy) {
        this.signedBy = signedBy;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public List<ScheduleTriggerNotification> getTriggerNotifications() {
        return triggerNotifications;
    }

    public void setTriggerNotifications(List<ScheduleTriggerNotification> triggerNotifications) {
        this.triggerNotifications = triggerNotifications;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public OperationRegion getRegion() {
        return region;
    }

    public void setRegion(OperationRegion region) {
        this.region = region;
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    public Boolean hasValidParent() {
        return parent != null && !parent.isNew();

    }

    public Boolean hasSigned(User user) {
        if (this.getSignedBy() == null) {
            this.signedBy = Lists.newArrayList();
            return Boolean.FALSE;
        } else {
            return this.signedBy.contains(user);
        }
    }
}
