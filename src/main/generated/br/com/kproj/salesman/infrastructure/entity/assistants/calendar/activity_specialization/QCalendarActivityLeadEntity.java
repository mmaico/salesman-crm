package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivityLeadEntity is a Querydsl query type for CalendarActivityLeadEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivityLeadEntity extends EntityPathBase<CalendarActivityLeadEntity> {

    private static final long serialVersionUID = 94399045L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivityLeadEntity calendarActivityLeadEntity = new QCalendarActivityLeadEntity("calendarActivityLeadEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.leads.QLeadEntity lead;

    public QCalendarActivityLeadEntity(String variable) {
        this(CalendarActivityLeadEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivityLeadEntity(Path<? extends CalendarActivityLeadEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivityLeadEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivityLeadEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivityLeadEntity.class, metadata, inits);
    }

    public QCalendarActivityLeadEntity(Class<? extends CalendarActivityLeadEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity(forProperty("activity"), inits.get("activity")) : null;
        this.lead = inits.isInitialized("lead") ? new br.com.kproj.salesman.infrastructure.entity.leads.QLeadEntity(forProperty("lead"), inits.get("lead")) : null;
    }

}

