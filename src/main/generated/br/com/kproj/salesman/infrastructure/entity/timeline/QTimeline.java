package br.com.kproj.salesman.infrastructure.entity.timeline;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTimeline is a Querydsl query type for Timeline
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTimeline extends EntityPathBase<Timeline> {

    private static final long serialVersionUID = 772235583L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeline timeline = new QTimeline("timeline");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity, br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity> activities = this.<br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity, br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity>createList("activities", br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity.class, br.com.kproj.salesman.infrastructure.entity.timeline.items.QTimelineActivity.class, PathInits.DIRECT2);

    public final br.com.kproj.salesman.infrastructure.entity.campaigns.QCampaignEntity campaign;

    public final br.com.kproj.salesman.infrastructure.entity.QContactEntity contact;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QIncidentEntity incident;

    public final br.com.kproj.salesman.infrastructure.entity.leads.QLeadEntity lead;

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson person;

    public final br.com.kproj.salesman.infrastructure.entity.activities.QPersonalActivityEntity personalActivity;

    public final br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposalEntity proposal;

    public final br.com.kproj.salesman.infrastructure.entity.task.QTaskEntity task;

    public QTimeline(String variable) {
        this(Timeline.class, forVariable(variable), INITS);
    }

    public QTimeline(Path<? extends Timeline> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimeline(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimeline(PathMetadata<?> metadata, PathInits inits) {
        this(Timeline.class, metadata, inits);
    }

    public QTimeline(Class<? extends Timeline> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.campaign = inits.isInitialized("campaign") ? new br.com.kproj.salesman.infrastructure.entity.campaigns.QCampaignEntity(forProperty("campaign"), inits.get("campaign")) : null;
        this.contact = inits.isInitialized("contact") ? new br.com.kproj.salesman.infrastructure.entity.QContactEntity(forProperty("contact"), inits.get("contact")) : null;
        this.incident = inits.isInitialized("incident") ? new br.com.kproj.salesman.infrastructure.entity.QIncidentEntity(forProperty("incident"), inits.get("incident")) : null;
        this.lead = inits.isInitialized("lead") ? new br.com.kproj.salesman.infrastructure.entity.leads.QLeadEntity(forProperty("lead"), inits.get("lead")) : null;
        this.person = inits.isInitialized("person") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("person"), inits.get("person")) : null;
        this.personalActivity = inits.isInitialized("personalActivity") ? new br.com.kproj.salesman.infrastructure.entity.activities.QPersonalActivityEntity(forProperty("personalActivity"), inits.get("personalActivity")) : null;
        this.proposal = inits.isInitialized("proposal") ? new br.com.kproj.salesman.infrastructure.entity.proposal.QBusinessProposalEntity(forProperty("proposal"), inits.get("proposal")) : null;
        this.task = inits.isInitialized("task") ? new br.com.kproj.salesman.infrastructure.entity.task.QTaskEntity(forProperty("task"), inits.get("task")) : null;
    }

}

