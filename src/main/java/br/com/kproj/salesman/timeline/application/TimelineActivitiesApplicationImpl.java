package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.TimelineActivitiesRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.infrastructure.service.FileApplication;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.events.messages.TimelineSaveMessage.createTimelineEvent;

@Service
public class TimelineActivitiesApplicationImpl extends BaseModelServiceImpl<TimelineActivity> implements TimelineActivitiesApplication {

	
    @Autowired
    private TimelineApplication service;

    @Autowired
    private TimelineActivitiesRepository repository;

    @Autowired
    private FileApplication fileApplication;

    @Autowired
    private EventBus eventBus;

    @Override
    public Timeline register(Person person, TimelineActivity activity) {

        Timeline timeline = service.register(person);
        saveActivity(activity, timeline);

        return timeline;
    }

    @Override
    public Timeline register(BusinessProposal proposal, TimelineActivity activity) {

        Timeline timeline = service.register(proposal);
        saveActivity(activity, timeline);

        eventBus.post(createTimelineEvent(activity.getUser(), proposal, activity));

        return timeline;
    }

    @Override
    public Timeline register(Contact contact, TimelineActivity activity) {

        Timeline timeline = service.register(contact);
        saveActivity(activity, timeline);

        return timeline;
    }

    @Override
    public Timeline register(Task task, TimelineActivity activity) {

        Timeline timeline = service.register(task);
        saveActivity(activity, timeline);

        return timeline;
    }

    @Override
    public Timeline register(CalendarActivity calendarActivity, TimelineActivity activity) {

        Timeline timeline = service.register(calendarActivity);
        saveActivity(activity, timeline);

        return timeline;
    }

    private void saveActivity(TimelineActivity activity, Timeline timeline) {
        List<AppFile> files = activity.getFiles();
        TimelineActivity activitySaved = save(activity);

        timeline.addActivity(activitySaved);
        this.fileApplication.saveFile(activitySaved, files);
    }

    @Override
    public byte[] getActivityFile(TimelineActivity timelineActivity, AppFile appfile) {
        if (timelineActivity.isNew() || appfile.isNew()) {
            return new byte[0];
        }
        return fileApplication.getFile(timelineActivity, appfile);
    }


    public BaseRepository<TimelineActivity, Long> getRepository() {
        return repository;
    }
}
