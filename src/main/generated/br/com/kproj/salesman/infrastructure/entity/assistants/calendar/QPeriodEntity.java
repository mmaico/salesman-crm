package br.com.kproj.salesman.infrastructure.entity.assistants.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPeriodEntity is a Querydsl query type for PeriodEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPeriodEntity extends EntityPathBase<PeriodEntity> {

    private static final long serialVersionUID = 579350538L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPeriodEntity periodEntity = new QPeriodEntity("periodEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final QCalendarActivity calendarActivity;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllDay = createBoolean("isAllDay");

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public QPeriodEntity(String variable) {
        this(PeriodEntity.class, forVariable(variable), INITS);
    }

    public QPeriodEntity(Path<? extends PeriodEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPeriodEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPeriodEntity(PathMetadata metadata, PathInits inits) {
        this(PeriodEntity.class, metadata, inits);
    }

    public QPeriodEntity(Class<? extends PeriodEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendarActivity = inits.isInitialized("calendarActivity") ? new QCalendarActivity(forProperty("calendarActivity"), inits.get("calendarActivity")) : null;
    }

}

