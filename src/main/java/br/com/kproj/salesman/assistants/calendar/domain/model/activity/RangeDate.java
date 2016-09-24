package br.com.kproj.salesman.assistants.calendar.domain.model.activity;

import br.com.kproj.salesman.infrastructure.model.ValueObject;

import java.util.Date;


public class RangeDate implements ValueObject {

    private final Date start;
    private final Date end;

    public RangeDate(Date startDate, Date endDate) {
        this.start = startDate;
        this.end = endDate;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
