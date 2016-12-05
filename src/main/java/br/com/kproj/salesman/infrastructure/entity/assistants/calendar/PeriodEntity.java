package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="periods")
public class PeriodEntity extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    private Date endDate;

    @Column(name="is_all_day")
    private Boolean isAllDay = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name="calendar_activity_id")
    private CalendarActivityEntity calendarActivityEntity;

    public CalendarActivityEntity getCalendarActivityEntity() {
        return calendarActivityEntity;
    }

    public void setCalendarActivityEntity(CalendarActivityEntity calendarActivityEntity) {
        this.calendarActivityEntity = calendarActivityEntity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getAllDay() {
        return isAllDay;
    }

    public Boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(Boolean allDay) {
        isAllDay = allDay;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
