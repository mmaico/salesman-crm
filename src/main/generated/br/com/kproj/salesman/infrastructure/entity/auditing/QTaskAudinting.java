package br.com.kproj.salesman.infrastructure.entity.auditing;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTaskAudinting is a Querydsl query type for TaskAudinting
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTaskAudinting extends EntityPathBase<TaskAudinting> {

    private static final long serialVersionUID = -501434010L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskAudinting taskAudinting = new QTaskAudinting("taskAudinting");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> entityId = createNumber("entityId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    public final DateTimePath<java.util.Date> lastUpdate = createDateTime("lastUpdate", java.util.Date.class);

    public final br.com.kproj.salesman.infrastructure.entity.QUser user;

    public QTaskAudinting(String variable) {
        this(TaskAudinting.class, forVariable(variable), INITS);
    }

    public QTaskAudinting(Path<? extends TaskAudinting> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskAudinting(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTaskAudinting(PathMetadata<?> metadata, PathInits inits) {
        this(TaskAudinting.class, metadata, inits);
    }

    public QTaskAudinting(Class<? extends TaskAudinting> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new br.com.kproj.salesman.infrastructure.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

