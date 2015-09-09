package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskItem is a Querydsl query type for TaskItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskItem extends EntityPathBase<TaskItem> {

    private static final long serialVersionUID = -1437103324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskItem taskItem = new QTaskItem("taskItem");

    public final QTimelineItem _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QTaskItem(String variable) {
        this(TaskItem.class, forVariable(variable), INITS);
    }

    public QTaskItem(Path<? extends TaskItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskItem(PathMetadata<?> metadata, PathInits inits) {
        this(TaskItem.class, metadata, inits);
    }

    public QTaskItem(Class<? extends TaskItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineItem(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

