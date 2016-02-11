package br.com.kproj.salesman.calendar.domain;

import br.com.kproj.salesman.infrastructure.entity.calendar.Period;

public interface PeriodDomainService {

    Boolean isValidPeriodToCalendarActivity(Period period);
}
