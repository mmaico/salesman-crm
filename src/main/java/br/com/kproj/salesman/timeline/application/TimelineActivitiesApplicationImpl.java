package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.TimelineActivitiesRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.infrastructure.service.FilePersistHelper;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.events.messages.TimelineSaveMessage.createTimelineEvent;

@Service
public class TimelineActivitiesApplicationImpl extends BaseModelServiceLegacyImpl<TimelineActivity> implements TimelineActivitiesApplication {

	
    @Autowired
    private TimelineApplication service;

    @Autowired
    private TimelineActivitiesRepository repository;

    @Autowired
    private FilePersistHelper filePersistHelper;

    @Autowired
    private EventBus eventBus;

    @Override
    public Timeline register(Person person, TimelineActivity activity) {

        Timeline timeline = service.register(person);
        saveActivity(activity, timeline);

        return timeline;
    }

    @Override
    public Timeline register(BusinessProposalEntity proposal, TimelineActivity activity) {

        Timeline timeline = service.register(proposal);
        saveActivity(activity, timeline);

        eventBus.post(createTimelineEvent(activity.getUser(), proposal, activity));

        return timeline;
    }

    @Override
    public Timeline register(ContactEntity contact, TimelineActivity activity) {

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
    public Timeline register(PersonalActivity activity, TimelineActivity item) {
        Timeline timeline = service.register(activity);
        saveActivity(item, timeline);

        return timeline;
    }

    @Override
    public Timeline register(Incident incident, TimelineActivity item) {
        Timeline timeline = service.register(incident);
        saveActivity(item, timeline);

        return timeline;
    }

    @Override
    public Timeline register(Lead lead, TimelineActivity item) {
        Timeline timeline = service.register(lead);
        saveActivity(item, timeline);

        return timeline;
    }

    private void saveActivity(TimelineActivity activity, Timeline timeline) {
        List<AppFile> files = activity.getFiles();
        TimelineActivity activitySaved = save(activity);

        timeline.addActivity(activitySaved);
        this.filePersistHelper.saveFile(activitySaved, files);
    }

    @Override
    public byte[] getActivityFile(TimelineActivity timelineActivity, AppFile appfile) {
        if (timelineActivity.isNew() || appfile.isNew()) {
            return new byte[0];
        }
        return filePersistHelper.getFile(timelineActivity, appfile);
    }


    public BaseRepositoryLegacy<TimelineActivity, Long> getRepository() {
        return repository;
    }

}
