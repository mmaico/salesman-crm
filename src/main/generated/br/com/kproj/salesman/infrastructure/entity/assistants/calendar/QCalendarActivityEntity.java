package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivityEntity is a Querydsl query type for CalendarActivityEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivityEntity extends EntityPathBase<CalendarActivityEntity> {

    private static final long serialVersionUID = 40152630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivityEntity calendarActivityEntity = new QCalendarActivityEntity("calendarActivityEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final BooleanPath allDay = createBoolean("allDay");

    public final QCalendarEntity calendar;

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> end = createDateTime("end", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final EnumPath<br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType> represent = createEnum("represent", br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType.class);

    public final DateTimePath<java.util.Date> start = createDateTime("start", java.util.Date.class);

    public final StringPath title = createString("title");

    public QCalendarActivityEntity(String variable) {
        this(CalendarActivityEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivityEntity(Path<? extends CalendarActivityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivityEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivityEntity.class, metadata, inits);
    }

    public QCalendarActivityEntity(Class<? extends CalendarActivityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new QCalendarEntity(forProperty("calendar"), inits.get("calendar")) : null;
    }

}

