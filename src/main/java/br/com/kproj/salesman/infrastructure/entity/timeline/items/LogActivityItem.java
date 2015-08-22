package br.com.kproj.salesman.infrastructure.entity.timeline.items;


import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("logactivity")
public class LogActivityItem extends TimelineItem {


    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private LogActivityTypeEnum type;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LogActivityTypeEnum getType() {
        return type;
    }

    public void setType(LogActivityTypeEnum type) {
        this.type = type;
    }
}
