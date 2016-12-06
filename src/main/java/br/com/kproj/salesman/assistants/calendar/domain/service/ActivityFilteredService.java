package br.com.kproj.salesman.assistants.calendar.domain.service;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@FunctionalInterface
public interface ActivityFilteredService {

    Iterable<Activity> using(FilterAggregator filters, Pageable page);
}
