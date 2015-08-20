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

    public static final QTimelineItem timelineItem = new QTimelineItem("timelineItem");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final DateTimePath<java.util.Date> creation = createDateTime("creation", java.util.Date.class);

    public final StringPath description = createString("description");

    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files = this.<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile>createList("files", br.com.kproj.salesman.infrastructure.entity.AppFile.class, br.com.kproj.salesman.infrastructure.entity.QAppFile.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QTimelineItem(String variable) {
        super(TimelineItem.class, forVariable(variable));
    }

    public QTimelineItem(Path<? extends TimelineItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimelineItem(PathMetadata<?> metadata) {
        super(TimelineItem.class, metadata);
    }

}

