package br.com.kproj.salesman.timelines.activities.domain.services;


import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;

@FunctionalInterface
public interface ActivityInTimelineService {

    Activity in(Timeline timeline);
}
