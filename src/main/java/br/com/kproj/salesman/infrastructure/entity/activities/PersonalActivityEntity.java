package br.com.kproj.salesman.infrastructure.entity.activities;

import br.com.kproj.salesman.infrastructure.configuration.annotations.IgnoreField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="personal_activity")
public class PersonalActivityEntity extends Identifiable implements TimelinePresent {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "personal.activity.title.is.null")
    private String title;
    private String description;

    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name="parent_id")
    private List<PersonalActivityEntity> activitiesChildren;

    @Column(name = "parent_id", updatable =false, insertable = false)
    private Long parentId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "personal.activity.deadline.not.informed")
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private PersonalAcvitityStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    private List<ActivityChecklistEntity> checklist;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name="assignment_id")
    private UserEntity assignment;

    @OneToOne
    @JoinColumn(name = "timeline_id")
    private Timeline timeline;


    @Transient
    @IgnoreField
    private PersonalActivityEntity parent;

    public PersonalActivityEntity(Long id) {
        this.id = id;
    }

    public PersonalActivityEntity() {}

    public void addChild(PersonalActivityEntity task) {
        if (this.activitiesChildren == null) {
            this.activitiesChildren = Lists.newArrayList();
        }
        this.activitiesChildren.add(task);
    }

    public void addCheckList(ActivityChecklistEntity checklist) {
        if (this.checklist == null) {
            this.checklist = Lists.newArrayList();
        }
        this.checklist.add(checklist);
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

    public List<PersonalActivityEntity> getActivitiesChildren() {
        return activitiesChildren;
    }

    public void setActivitiesChildren(List<PersonalActivityEntity> activitiesChildren) {
        this.activitiesChildren = activitiesChildren;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<ActivityChecklistEntity> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<ActivityChecklistEntity> checklist) {
        this.checklist = checklist;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public PersonalActivityEntity getParent() {
        return parent;
    }

    public void setParent(PersonalActivityEntity parent) {
        this.parent = parent;
    }

    public Boolean hasValidParent() {
        return parent != null && !parent.isNew();
    }

    public PersonalAcvitityStatus getStatus() {
        return status;
    }

    public void setStatus(PersonalAcvitityStatus status) {
        this.status = status;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public UserEntity getAssignment() {
        return assignment;
    }

    public void setAssignment(UserEntity assignment) {
        this.assignment = assignment;
    }
}
