package br.com.kproj.salesman.infrastructure.entity.activities;

import br.com.kproj.salesman.infrastructure.configuration.annotations.IgnoreField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;
import com.google.common.collect.Lists;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="personal_activity")
public class PersonalActivityEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "personal.activity.title.is.null")
    private String title;
    private String description;

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
    @JoinColumn(name="assigner_id")
    private UserEntity assigner;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ActivityTypeEntity type;


    @Transient
    @IgnoreField
    private PersonalActivityEntity parent;

    public PersonalActivityEntity(Long id) {
        this.id = id;
    }

    public PersonalActivityEntity() {}

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


    public PersonalActivityEntity getParent() {
        return parent;
    }

    public void setParent(PersonalActivityEntity parent) {
        this.parent = parent;
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

    public UserEntity getAssigner() {
        return assigner;
    }

    public void setAssigner(UserEntity assigner) {
        this.assigner = assigner;
    }

    public ActivityTypeEntity getType() {
        return type;
    }

    public void setType(ActivityTypeEntity type) {
        this.type = type;
    }
}
