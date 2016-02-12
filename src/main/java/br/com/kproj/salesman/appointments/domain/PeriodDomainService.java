package br.com.kproj.salesman.appointments.domain;

import br.com.kproj.salesman.infrastructure.entity.calendar.Period;

public interface PeriodDomainService {

    Boolean isValidPeriodToCalendarActivity(Period period);
}
