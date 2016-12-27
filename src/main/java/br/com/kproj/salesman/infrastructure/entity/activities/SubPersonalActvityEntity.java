package br.com.kproj.salesman.infrastructure.entity.activities;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="personal_activity_sub")
public class SubPersonalActvityEntity extends Identifiable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "personal_activity_id")
    private PersonalActivityEntity activity;

    @ManyToOne
    @JoinColumn(name="root_personal_activity_id")
    private RootPersonalActivityEntity parent;

    public SubPersonalActvityEntity(Long id) {
        this.id = id;
    }
    public SubPersonalActvityEntity() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(PersonalActivityEntity activity) {
        this.activity = activity;
    }

    public RootPersonalActivityEntity getParent() {
        return parent;
    }

    public void setParent(RootPersonalActivityEntity parent) {
        this.parent = parent;
    }
}
