package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface TimelineActivitiesApplication extends ModelLegacyService<TimelineActivity> {

    Timeline register(Person person, TimelineActivity item);

    Timeline register(BusinessProposalEntity proposal, TimelineActivity item);

    Timeline register(ContactEntity contact, TimelineActivity item);

    Timeline register(TaskEntity taskEntity, TimelineActivity item);

    Timeline register(PersonalActivity activity, TimelineActivity item);

    Timeline register(Incident incident, TimelineActivity item);

    Timeline register(Lead lead, TimelineActivity item);

    byte[] getActivityFile(TimelineActivity timelineActivity, AppFile appfile);

}
