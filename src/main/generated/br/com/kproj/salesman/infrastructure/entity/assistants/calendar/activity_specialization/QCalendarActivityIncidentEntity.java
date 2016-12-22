package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivityIncidentEntity is a Querydsl query type for CalendarActivityIncidentEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivityIncidentEntity extends EntityPathBase<CalendarActivityIncidentEntity> {

    private static final long serialVersionUID = -1467597765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivityIncidentEntity calendarActivityIncidentEntity = new QCalendarActivityIncidentEntity("calendarActivityIncidentEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QIncidentEntity incident;

    public QCalendarActivityIncidentEntity(String variable) {
        this(CalendarActivityIncidentEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivityIncidentEntity(Path<? extends CalendarActivityIncidentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivityIncidentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivityIncidentEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivityIncidentEntity.class, metadata, inits);
    }

    public QCalendarActivityIncidentEntity(Class<? extends CalendarActivityIncidentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity(forProperty("activity"), inits.get("activity")) : null;
        this.incident = inits.isInitialized("incident") ? new br.com.kproj.salesman.infrastructure.entity.QIncidentEntity(forProperty("incident"), inits.get("incident")) : null;
    }

}

