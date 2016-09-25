package br.com.kproj.salesman.infrastructure.entity;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QIncident is a Querydsl query type for Incident
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIncident extends EntityPathBase<IncidentEntity> {

    private static final long serialVersionUID = 985618433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIncident incident = new QIncident("incident");

    public final QIdentifiable _super = new QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.person.QPerson client;

    public final QContactEntity contact;

    public final QUserEntity createdBy;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<IncidentEntity.IncidentPriority> priority = createEnum("priority", IncidentEntity.IncidentPriority.class);

    public final QUserEntity responsible;

    public final EnumPath<IncidentEntity.IncidentStatus> status = createEnum("status", IncidentEntity.IncidentStatus.class);

    public final br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline timeline;

    public final StringPath title = createString("title");

    public QIncident(String variable) {
        this(IncidentEntity.class, forVariable(variable), INITS);
    }

    public QIncident(Path<? extends IncidentEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIncident(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QIncident(PathMetadata<?> metadata, PathInits inits) {
        this(IncidentEntity.class, metadata, inits);
    }

    public QIncident(Class<? extends IncidentEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("client"), inits.get("client")) : null;
        this.contact = inits.isInitialized("contact") ? new QContactEntity(forProperty("contact"), inits.get("contact")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.responsible = inits.isInitialized("responsible") ? new QUserEntity(forProperty("responsible"), inits.get("responsible")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

