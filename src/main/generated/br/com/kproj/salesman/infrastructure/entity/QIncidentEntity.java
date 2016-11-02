package br.com.kproj.salesman.infrastructure.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIncidentEntity is a Querydsl query type for IncidentEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIncidentEntity extends EntityPathBase<IncidentEntity> {

    private static final long serialVersionUID = -993631420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIncidentEntity incidentEntity = new QIncidentEntity("incidentEntity");

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

    public QIncidentEntity(String variable) {
        this(IncidentEntity.class, forVariable(variable), INITS);
    }

    public QIncidentEntity(Path<? extends IncidentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIncidentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIncidentEntity(PathMetadata metadata, PathInits inits) {
        this(IncidentEntity.class, metadata, inits);
    }

    public QIncidentEntity(Class<? extends IncidentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.client = inits.isInitialized("client") ? new br.com.kproj.salesman.infrastructure.entity.person.QPerson(forProperty("client"), inits.get("client")) : null;
        this.contact = inits.isInitialized("contact") ? new QContactEntity(forProperty("contact"), inits.get("contact")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.responsible = inits.isInitialized("responsible") ? new QUserEntity(forProperty("responsible"), inits.get("responsible")) : null;
        this.timeline = inits.isInitialized("timeline") ? new br.com.kproj.salesman.infrastructure.entity.timeline.QTimeline(forProperty("timeline"), inits.get("timeline")) : null;
    }

}

