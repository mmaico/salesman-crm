package br.com.kproj.salesman.infrastructure.entity.timeline.items;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;

import static com.mysema.query.types.PathMetadataFactory.forVariable;


/**
 * QTaskActivity is a Querydsl query type for TaskActivity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskActivity extends EntityPathBase<TaskActivity> {

    private static final long serialVersionUID = -1495690592L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskActivity taskActivity = new QTaskActivity("taskActivity");

    public final QTimelineActivity _super;

    //inherited
    public final DateTimePath<java.util.Date> creation;

    //inherited
    public final StringPath description;

    //inherited
    public final ListPath<br.com.kproj.salesman.infrastructure.entity.AppFile, br.com.kproj.salesman.infrastructure.entity.QAppFile> files;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final br.com.kproj.salesman.infrastructure.entity.QUserEntity user;

    public QTaskActivity(String variable) {
        this(TaskActivity.class, forVariable(variable), INITS);
    }

    public QTaskActivity(Path<? extends TaskActivity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskActivity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskActivity(PathMetadata<?> metadata, PathInits inits) {
        this(TaskActivity.class, metadata, inits);
    }

    public QTaskActivity(Class<? extends TaskActivity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QTimelineActivity(type, metadata, inits);
        this.creation = _super.creation;
        this.description = _super.description;
        this.files = _super.files;
        this.id = _super.id;
        this.user = _super.user;
    }

}

