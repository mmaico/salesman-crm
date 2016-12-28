package br.com.kproj.salesman.infrastructure.entity.activities;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="personal_activity_checklists")
public class ActivityChecklistEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "personal.activity.checklist.name.invalid")
    private String name;

    @Column(name="is_done")
    private Boolean done;

    @ManyToOne
    @JoinColumn(name="personal_activity_id")
    @NotNull(message = "personal.activity.checklist.task.null")
    private PersonalActivityEntity activity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDone() {
        return this.done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public PersonalActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(PersonalActivityEntity activity) {
        this.activity = activity;
    }
}
