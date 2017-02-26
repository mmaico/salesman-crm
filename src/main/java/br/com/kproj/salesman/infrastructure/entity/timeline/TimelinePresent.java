package br.com.kproj.salesman.infrastructure.entity.timeline;


public interface TimelinePresent {

    Long getId();

    TimelineEntity getTimeline();

    void setTimeline(TimelineEntity timeline);
}
