package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="activity_type")
public class ActivityType extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Override
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
}
