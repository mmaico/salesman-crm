package br.com.kproj.salesman.appointments.application.dto;


import br.com.kproj.salesman.infrastructure.helpers.DateHelper;

import java.util.Date;

public class RangeDatesDTO {

    private Date startDate;

    private Date endDate;

    public RangeDatesDTO(String startDate, String endDate) {
        Date startDateConveted = DateHelper.convertToDate(startDate);
        Date endDateConverted = DateHelper.convertToDate(endDate);

        this.startDate = startDateConveted == null ? new Date() : startDateConveted;
        this.endDate = endDateConverted;
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

    public Boolean hasValidRangeToSearch() {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    public static RangeDatesDTO create(String startDate, String endDate) {
        return new RangeDatesDTO(startDate, endDate);
    }
}
