package br.com.kproj.salesman.infrastructure.entity.activities;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.*;

@Entity
@Table(name="personal_activity_root")
public class RootPersonalActivityEntity extends Identifiable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "personal_activity_id")
    private PersonalActivityEntity activity;

    public RootPersonalActivityEntity(Long id) {
        this.id = id;
    }

    public RootPersonalActivityEntity() {}

    @Override
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
}
