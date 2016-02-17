package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="calendars")
public class Calendar extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "calendar")
    private List<CalendarActivity> activities;

    @OneToOne(mappedBy = "calendar")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
