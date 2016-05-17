package br.com.kproj.salesman.assistants.calendar.view;


import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class PeriodDTO {

    private static final String PATTERN_DATE = "dd/MM/yyyy HH:mm";
    private static final String DEFAULT_HOUR = "00:00";

    private String startDate = StringUtils.EMPTY;
    private String startHour = DEFAULT_HOUR;

    private String endDate = StringUtils.EMPTY;
    private String endHour = DEFAULT_HOUR;

    private Boolean isAllDay = Boolean.FALSE;

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public Boolean getAllDay() {
        return isAllDay;
    }

    public void setAllDay(Boolean allDay) {
        isAllDay = allDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Date getFullStartDate() {
        String startHourTreat = this.isAllDay ? DEFAULT_HOUR : this.startHour;

        String date = startDate.trim() + " " + startHourTreat.trim();

        Date dateFormatted = DateHelper.convertToDate(date, PATTERN_DATE);

        if (dateFormatted == null) {
            throw new ValidationException(Sets.newHashSet("start.date.or.hour.is.invalid"));
        }

        return dateFormatted;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Date getFullEndDate() {
        String endHourTreat = this.isAllDay ? DEFAULT_HOUR : this.endHour;
        String date = endDate.trim() + " " + endHourTreat.trim();

        Date dateFormatted = DateHelper.convertToDate(date, PATTERN_DATE);

        if (dateFormatted == null) {
            throw new ValidationException(Sets.newHashSet("end.date.or.hour.is.invalid"));
        }

        return dateFormatted;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
