package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.service;


import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ActivityFilteredService {

    Iterable<Activity> using(FilterAggregator filters, Pageable page);
}
