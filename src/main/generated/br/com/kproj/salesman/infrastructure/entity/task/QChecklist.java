package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChecklist is a Querydsl query type for Checklist
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChecklist extends EntityPathBase<Checklist> {

    private static final long serialVersionUID = -1360134100L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklist checklist = new QChecklist("checklist");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDone = createBoolean("isDone");

    public final StringPath name = createString("name");

    public final QTask task;

    public QChecklist(String variable) {
        this(Checklist.class, forVariable(variable), INITS);
    }

    public QChecklist(Path<? extends Checklist> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklist(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklist(PathMetadata<?> metadata, PathInits inits) {
        this(Checklist.class, metadata, inits);
    }

    public QChecklist(Class<? extends Checklist> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTask(forProperty("task"), inits.get("task")) : null;
    }

}

