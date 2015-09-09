package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTimelineItem is a Querydsl query type for TimelineItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTimelineItem extends EntityPathBase<TimelineItem> {

    private static final long serialVersionUID = -1067837984L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimelineItem timelineItem = new QTimelineItem("timelineItem");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final StringPath description = createString("description");

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = this.<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile>createList("files", br.com.kproj.salesman.infrastructure.entity.AppFile.class, br.com.kproj.salesman.infrastructure.entity.QAppFile.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QTimelineItem(String variable) {
        this(TimelineItem.class, forVariable(variable), INITS);
    }

    public QTimelineItem(Path<? extends TimelineItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimelineItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTimelineItem(PathMetadata<?> metadata, PathInits inits) {
        this(TimelineItem.class, metadata, inits);
    }

    public QTimelineItem(Class<? extends TimelineItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

