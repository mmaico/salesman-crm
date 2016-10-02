package br.com.kproj.salesman.infrastructure.entity.task;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChecklistEntity is a Querydsl query type for ChecklistEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChecklistEntity extends EntityPathBase<ChecklistEntity> {

    private static final long serialVersionUID = -670892241L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChecklistEntity checklistEntity = new QChecklistEntity("checklistEntity");

    public final br.com.kproj.salesman.infrastructure.entity.QIdentifiable _super = new br.com.kproj.salesman.infrastructure.entity.QIdentifiable(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDone = createBoolean("isDone");

    public final StringPath name = createString("name");

    public final QTaskEntity task;

    public QChecklistEntity(String variable) {
        this(ChecklistEntity.class, forVariable(variable), INITS);
    }

    public QChecklistEntity(Path<? extends ChecklistEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChecklistEntity(PathMetadata<?> metadata, PathInits inits) {
        this(ChecklistEntity.class, metadata, inits);
    }

    public QChecklistEntity(Class<? extends ChecklistEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.task = inits.isInitialized("task") ? new QTaskEntity(forProperty("task"), inits.get("task")) : null;
    }

}

