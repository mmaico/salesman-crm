package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.configuration.annotations.IgnoreField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="delivery")
public class TaskEntity extends Identifiable implements TimelinePresent {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "task.title.is.null")
    private String title;
    private String description;

    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name="parent_id")
    @ExcludeField
    private List<TaskEntity> tasksChildren;

    @Column(name = "parent_id", updatable =false, insertable = false)
    private Long parentId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "task.deadline.not.informed")
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatusEntity status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<ChecklistEntity> checklistEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<TaskCost> taskCosts;

    @ManyToOne
    @JoinColumn(name="sales_order_id")
    @ExcludeField
    private SalesOrderEntity salesOrderEntity;

    @ManyToMany
    @JoinTable(name="task_user",
            joinColumns={@JoinColumn(name="task_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<UserEntity> signedBy;

    @OneToOne
    @JoinColumn(name = "timeline_id")
    @ExcludeField
    private Timeline timeline;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<ScheduleTriggerNotification> triggerNotifications;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    private OperationRegionEntity region;

    @Transient
    @IgnoreField
    @ExcludeField
    private TaskEntity parent;


    public TaskEntity() {}
    public TaskEntity(Long id) {
        this.id = id;
    }

    public void addChild(TaskEntity taskEntity) {
        if (this.tasksChildren == null) {
            this.tasksChildren = Lists.newArrayList();
        }
        this.tasksChildren.add(taskEntity);
    }

    public void addCheckList(ChecklistEntity checklistEntity) {
        if (this.checklistEntity == null) {
            this.checklistEntity = Lists.newArrayList();
        }
        this.checklistEntity.add(checklistEntity);
    }

    public void addTaskCost(TaskCost taskCost) {
        if (this.taskCosts == null) {
            this.taskCosts = Lists.newArrayList();
        }
        this.taskCosts.add(taskCost);
    }

    public void addSignedBy(UserEntity user) {
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

    public List<TaskEntity> getTasksChildren() {
        return tasksChildren;
    }

    public void setTasksChildren(List<TaskEntity> tasksChildren) {
        this.tasksChildren = tasksChildren;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatusEntity getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEntity status) {
        this.status = status;
    }

    public List<ChecklistEntity> getChecklistEntity() {
        return checklistEntity;
    }

    public void setChecklistEntity(List<ChecklistEntity> checklistEntity) {
        this.checklistEntity = checklistEntity;
    }

    public List<TaskCost> getTaskCosts() {
        return taskCosts;
    }

    public void setTaskCosts(List<TaskCost> taskCosts) {
        this.taskCosts = taskCosts;
    }

    public List<UserEntity> getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(List<UserEntity> signedBy) {
        this.signedBy = signedBy;
    }

    public SalesOrderEntity getSalesOrderEntity() {
        return salesOrderEntity;
    }

    public void setSalesOrderEntity(SalesOrderEntity salesOrderEntity) {
        this.salesOrderEntity = salesOrderEntity;
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

    public OperationRegionEntity getRegion() {
        return region;
    }

    public void setRegion(OperationRegionEntity region) {
        this.region = region;
    }

    public TaskEntity getParent() {
        return parent;
    }

    public void setParent(TaskEntity parent) {
        this.parent = parent;
    }

    public Boolean hasValidParent() {
        return parent != null && !parent.isNew();

    }

    public Boolean hasSigned(UserEntity user) {
        if (this.getSignedBy() == null) {
            this.signedBy = Lists.newArrayList();
            return Boolean.FALSE;
        } else {
            return this.signedBy.contains(user);
        }
    }
}
