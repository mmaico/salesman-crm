package br.com.kproj.salesman.assistants.calendar.view;


import org.apache.commons.lang3.StringUtils;

public class PeriodDTO {

    private String startDate = StringUtils.EMPTY;
    private String startHour = StringUtils.EMPTY;

    private String endDate = StringUtils.EMPTY;
    private String endHour = StringUtils.EMPTY;

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

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
