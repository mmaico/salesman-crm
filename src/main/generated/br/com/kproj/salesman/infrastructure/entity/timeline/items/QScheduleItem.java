package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QScheduleItem is a Querydsl query type for ScheduleItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QScheduleItem extends EntityPathBase<ScheduleItem> {

    private static final long serialVersionUID = -1372276426L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleItem scheduleItem = new QScheduleItem("scheduleItem");

    public final QTimelineItem _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final StringPath title = createString("title");

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QScheduleItem(String variable) {
        this(ScheduleItem.class, forVariable(variable), INITS);
    }

    public QScheduleItem(Path<? extends ScheduleItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QScheduleItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QScheduleItem(PathMetadata<?> metadata, PathInits inits) {
        this(ScheduleItem.class, metadata, inits);
    }

    public QScheduleItem(Class<? extends ScheduleItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineItem(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

