package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import br.com.kproj.salesman.infrastructure.configuration.ExcludeField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="calendars")
public class CalendarEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "calendarEntity")
    private List<CalendarActivity> activities;

    @OneToOne(mappedBy = "calendarEntity")
    @ExcludeField
    private UserEntity user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CalendarActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<CalendarActivity> activities) {
        this.activities = activities;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
