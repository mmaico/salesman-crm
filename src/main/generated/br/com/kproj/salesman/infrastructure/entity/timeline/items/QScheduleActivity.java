package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.querydsl.core.types.PathMetadataFactory.*;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScheduleActivity is a Querydsl query type for ScheduleActivity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QScheduleActivity extends EntityPathBase<ScheduleActivity> {

    private static final long serialVersionUID = -43161678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleActivity scheduleActivity = new QScheduleActivity("scheduleActivity");

    public final QTimelineActivity _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final ListPath<AppFileEntity, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath title = createString("title");

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QScheduleActivity(String variable) {
        this(ScheduleActivity.class, forVariable(variable), INITS);
    }

    public QScheduleActivity(Path<? extends ScheduleActivity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScheduleActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScheduleActivity(PathMetadata metadata, PathInits inits) {
        this(ScheduleActivity.class, metadata, inits);
    }

    public QScheduleActivity(Class<? extends ScheduleActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

