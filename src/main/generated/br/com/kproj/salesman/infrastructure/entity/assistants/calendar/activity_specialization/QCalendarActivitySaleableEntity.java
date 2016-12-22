package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarActivitySaleableEntity is a Querydsl query type for CalendarActivitySaleableEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCalendarActivitySaleableEntity extends EntityPathBase<CalendarActivitySaleableEntity> {

    private static final long serialVersionUID = -850699702L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarActivitySaleableEntity calendarActivitySaleableEntity = new QCalendarActivitySaleableEntity("calendarActivitySaleableEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity activity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity saleable;

    public QCalendarActivitySaleableEntity(String variable) {
        this(CalendarActivitySaleableEntity.class, forVariable(variable), INITS);
    }

    public QCalendarActivitySaleableEntity(Path<? extends CalendarActivitySaleableEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarActivitySaleableEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarActivitySaleableEntity(PathMetadata metadata, PathInits inits) {
        this(CalendarActivitySaleableEntity.class, metadata, inits);
    }

    public QCalendarActivitySaleableEntity(Class<? extends CalendarActivitySaleableEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new br.com.kproj.salesman.infrastructure.entity.assistants.calendar.QCalendarActivityEntity(forProperty("activity"), inits.get("activity")) : null;
        this.saleable = inits.isInitialized("saleable") ? new br.com.kproj.salesman.infrastructure.entity.saleable.QSaleableUnitEntity(forProperty("saleable")) : null;
    }

}

