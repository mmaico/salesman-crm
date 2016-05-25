package br.com.kproj.salesman.infrastructure.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIncident is a Querydsl query type for Incident
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIncident extends EntityPathBase<Incident> {

    private static final long serialVersionUID = 985618433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIncident incident = new QIncident("incident");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson client;

    public final QContact contact;

    public final QUser createdBy;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Incident.IncidentPriority> priority = createEnum("priority", Incident.IncidentPriority.class);

    public final QUser responsible;

    public final EnumPath<Incident.IncidentStatus> status = createEnum("status", Incident.IncidentStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public QIncident(String variable) {
        this(Incident.class, forVariable(variable), INITS);
    }

    public QIncident(Path<? extends Incident> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIncident(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIncident(PathMetadata<?> metadata, PathInits inits) {
        this(Incident.class, metadata, inits);
    }

    public QIncident(Class<? extends Incident> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("client"), inits.get("client")) : null;
        this.contact = inits.isInitialized("contact") ? new QContact(forProperty("contact"), inits.get("contact")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new QUser(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.responsible = inits.isInitialized("responsible") ? new QUser(forProperty("responsible"), inits.get("responsible")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

