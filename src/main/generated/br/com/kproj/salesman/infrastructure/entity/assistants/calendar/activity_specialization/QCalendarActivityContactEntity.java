package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivityContactEntity is a Querydsl query type for CalendarActivityContactEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivityContactEntity extends EntityPathBase<CalendarActivityContactEntity> {

    private static final long serialVersionUID = 138648189L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivityContactEntity calendarActivityContactEntity = new QCalendarActivityContactEntity("calendarActivityContactEntity");

    public final br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity activity;

    public final br.com.kproj.salesman.infrastructure.entity.QContactEntity contact;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCalendarActivityContactEntity(String variable) {
        this(CalendarActivityContactEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivityContactEntity(Path<? extends CalendarActivityContactEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivityContactEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivityContactEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivityContactEntity.class, metadata, inits);
    }

    public QCalendarActivityContactEntity(Class<? extends CalendarActivityContactEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity(forProperty("activity"), inits.get("activity")) : null;
        this.contact = inits.isInitialized("contact") ? new br.com.kproj.salesman.infrastructure.entity.QContactEntity(forProperty("contact"), inits.get("contact")) : null;
    }

}

