package br.com.kproj.salesman.infrastructure.entity.task;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="task_delivery")
public class TaskEntity extends Identifiable implements TimelinePresent {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "task.title.is.null")
    private String title;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "task.deadline.not.informed")
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatusEntity status;

    @OneToMany(mappedBy = "task")
    private List<ChecklistEntity> checklist;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "task")
    private List<TaskResponsibleEntity> responsibles;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    @NotNull
    private DeliveryEntity delivery;

    @OneToOne
    @JoinColumn(name = "timeline_id")
    @ExcludeField
    private Timeline timeline;

    @ManyToOne
    @JoinColumn(name="operation_region_id")
    private OperationRegionEntity region;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TaskTypeEntity type;

    public TaskEntity() {}
    public TaskEntity(Long id) {
        this.id = id;
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

    public List<ChecklistEntity> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<ChecklistEntity> checklist) {
        this.checklist = checklist;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public OperationRegionEntity getRegion() {
        return region;
    }

    public void setRegion(OperationRegionEntity region) {
        this.region = region;
    }

    public List<TaskResponsibleEntity> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(List<TaskResponsibleEntity> responsibles) {
        this.responsibles = responsibles;
    }

    public DeliveryEntity getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryEntity delivery) {
        this.delivery = delivery;
    }


    public TaskTypeEntity getType() {
        return type;
    }

    public void setType(TaskTypeEntity type) {
        this.type = type;
    }
}
