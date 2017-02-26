package br.com.kproj.salesman.timelines.timeline.domain.model.timeline;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

public interface TimelineRepository extends BaseRepository<Timeline, Long> {

    Timeline newTimeline();
}
