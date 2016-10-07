package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivity is a Querydsl query type for CalendarActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivity extends EntityPathBase<CalendarActivity> {

    private static final long serialVersionUID = 1944404083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivity calendarActivity = new QCalendarActivity("calendarActivity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QCalendarEntity calendar;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final QPeriodEntity period;

    public final StringPath title = createString("title");

    public final QActivityType type;

    public QCalendarActivity(String variable) {
        this(CalendarActivity.class, forVariable(variable), INITS);
    }

    public QCalendarActivity(Path<? extends CalendarActivity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivity.class, metadata, inits);
    }

    public QCalendarActivity(Class<? extends CalendarActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new QCalendarEntity(forProperty("calendar"), inits.get("calendar")) : null;
        this.period = inits.isInitialized("period") ? new QPeriodEntity(forProperty("period"), inits.get("period")) : null;
        this.type = inits.isInitialized("type") ? new QActivityType(forProperty("type")) : null;
    }

}

